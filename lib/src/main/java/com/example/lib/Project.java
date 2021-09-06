package com.example.lib;

import java.util.HashSet;

public class Project implements IProject {
    private String name;
    private HashSet<ITask> tasks;

    public Project(String name) {
        this.name = name;
    }

    public Project(HashSet<ITask> tasks) {
        this.tasks = tasks;
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
        else {
            tasks.add(t);
        }
    }

    @Override
    public void removeTask(ITask t) throws NotFoundException {
        if (!tasks.contains(t)) {
            throw new NotFoundException();
        }
        else {
            tasks.remove(t);
        }
    }
}
