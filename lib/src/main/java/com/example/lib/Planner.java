package com.example.lib;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Planner implements IPlanner {
    private final HashMap<String, IBoard> boards;
    private final HashMap<String, IProject> projects;

    public Planner() {
        this.boards = new HashMap<>();
        this.projects = new HashMap<>();
    }

    @Override
    public void addBoard(IBoard b) throws AlreadyExistsException {
        if (boards.containsKey(b.getName())) {
            throw new AlreadyExistsException();
        }
        boards.put(b.getName(), b);
    }

    @Override
    public void addProject(IProject p) throws AlreadyExistsException {
        if (projects.containsKey(p.getName())) {
            throw new AlreadyExistsException();
        }
        projects.put(p.getName(), p);
    }

    @Override
    public Iterable<IBoard> getBoards() {
        return boards.values();
    }

    @Override
    public Iterable<IProject> getProjects() {
        return projects.values();
    }

    @Override
    public String writeXMLData() {
        StringBuilder boardsXml = new StringBuilder("\t<boards>\n");
        for (IBoard b : boards.values()) {
            boardsXml.append(((Board) b).toXML(2));
        }
        boardsXml.append("\t</boards>\n");

        StringBuilder projectsXml = new StringBuilder("\t<projects>\n");
        for (IProject p : projects.values()) {
            projectsXml.append(((Project) p).toXML(2));
        }
        projectsXml.append("\t</projects>\n");

        return "<planner>\n" + boardsXml + projectsXml + "</planner>";
    }

    public ITask readTaskXML(Element taskElement) {
        String taskName = taskElement.getAttribute("name");
        String taskDescription = taskElement.getAttribute("description");
        String taskDurationString = taskElement.getAttribute("expected-duration");
        Duration taskDuration = Duration.parse(taskDurationString);
        ITask task = new Task(taskName, taskDescription, taskDuration);

        NodeList taskNodes = taskElement.getElementsByTagName("task");
        for (int i = 0; i < taskNodes.getLength(); i++) {
            Element subTaskElement = (Element) taskNodes.item(i);
            ITask subTask = readTaskXML(subTaskElement);
            try {
                task.addSubTask(subTask);
            } catch (AlreadyExistsException e) {
                e.printStackTrace();
            }
        }

        return task;
    }

    @Override
    public void readXMLData(String data) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            assert builder != null;
            document = builder.parse(new InputSource(new StringReader(data)));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        assert document != null;
        Element root = document.getDocumentElement();

        NodeList boardNodes = root.getElementsByTagName("board");
        for (int i = 0; i < boardNodes.getLength(); i++) {
            Element boardElement = (Element) boardNodes.item(i);
            String boardName = boardElement.getAttribute("name");
            Board board = new Board(boardName);
            try {
                addBoard(board);
            } catch (AlreadyExistsException e) {
                e.printStackTrace();
            }

            NodeList sectionNodes = boardElement.getElementsByTagName("section");
            for (int j = 0; j < sectionNodes.getLength(); j++) {
                Element sectionElement = (Element) sectionNodes.item(j);
                String sectionName = sectionElement.getAttribute("name");
                Section section = new Section(sectionName);
                try {
                    board.addSection(section);
                } catch (AlreadyExistsException e) {
                    e.printStackTrace();
                }

                NodeList taskNodes = sectionElement.getElementsByTagName("task");
                for (int k = 0; k < taskNodes.getLength(); k++) {
                    Element taskElement = (Element) taskNodes.item(k);
                    ITask task = readTaskXML(taskElement);
                    try {
                        section.addTask(task);
                    } catch (AlreadyExistsException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        NodeList projectNodes = root.getElementsByTagName("project");
        for (int i = 0; i < projectNodes.getLength(); i++) {
            Element projectElement = (Element) projectNodes.item(i);
            String projectName = projectElement.getAttribute("name");
            Project project = new Project(projectName);
            try {
                addProject(project);
            } catch (AlreadyExistsException e) {
                e.printStackTrace();
            }

            NodeList taskNodes = projectElement.getElementsByTagName("task");
            for (int k = 0; k < taskNodes.getLength(); k++) {
                Element taskElement = (Element) taskNodes.item(k);
                ITask task = readTaskXML(taskElement);
                try {
                    project.addTask(task);
                } catch (AlreadyExistsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class AlreadyExistsException extends Exception {}
class NotFoundException extends Exception {}
