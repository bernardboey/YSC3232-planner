package com.example.lib;

import java.util.HashMap;
import java.io.StringReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

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
        StringBuilder boardsXml = new StringBuilder("<boards>");
        for (IBoard b : boards.values()) {
            boardsXml.append(((Board) b).toXML());
        }
        boardsXml.append("</boards>");

        StringBuilder projectsXml = new StringBuilder("<projects>");
        for (IProject p : projects.values()) {
            projectsXml.append(((Project) p).toXML());
        }
        projectsXml.append("</projects>");

        return "<planner>" + boardsXml + projectsXml + "</planner>";
    }

    @Override
    public void readXMLData(String data) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(data)));

        Element root = document.getDocumentElement();

    }
}

class AlreadyExistsException extends Exception {}
class NotFoundException extends Exception {}
