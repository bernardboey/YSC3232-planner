package com.example.lib;

import java.util.ArrayList;

public class Project implements IProject {
    private final String name;
    private final ArrayList<ITask> tasks;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Iterable<ITask> getTasks() {
        return tasks;
    }

    @Override
    public void addTask(ITask t) throws AlreadyExistsException {
        if (tasks.contains(t)) {
            throw new AlreadyExistsException();
        }
        tasks.add(t);
    }

    @Override
    public void removeTask(ITask t) throws NotFoundException {
        if (!tasks.remove(t)) {
            throw new NotFoundException();
        }
    }

    @Override
    public String toXML(int indentationLevel) {
        String indentation = "\t".repeat(indentationLevel);

        StringBuilder projectXML = new StringBuilder(
                String.format("%s<project name='%s'>\n", indentation, name)
        );

        projectXML.append(indentation).append("\t<tasks>\n");
        for (ITask t : tasks) {
            projectXML.append(t.toXML(indentationLevel + 2));
        }
        projectXML.append(indentation).append("\t</tasks>\n");

        projectXML.append(indentation).append("</project>\n");
        return projectXML.toString();
    }
}
