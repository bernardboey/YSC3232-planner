package com.example.lib;

import java.util.HashSet;

public class Project implements IProject {
    private final String name;
    private final HashSet<ITask> tasks;

    public Project(String name) {
        this.name = name;
        this.tasks = new HashSet<>();
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

    public String toXML() {
        String xmlstring = "<project";
        String name = " name='" + getName() + "'>" + "<tasks>";

        StringBuilder tasks_string = new StringBuilder();

        for (ITask t : tasks) {
            String task_xml = ((Task)t).toXML();
            tasks_string.append(task_xml);
        }

        xmlstring += name + tasks_string + "</tasks>" + "</project>";
        return xmlstring;
    }

    public void fromXML(String data) {
        //TODO
    }
}
