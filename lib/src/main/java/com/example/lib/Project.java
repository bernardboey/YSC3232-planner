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
        if (!tasks.contains(t)) {
            throw new NotFoundException();
        }
        tasks.remove(t);
    }
}
