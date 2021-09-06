package com.example.lib;

import java.util.ArrayList;

public class Section implements ISection {
    private final String name;
    private final ArrayList<ITask> tasks;

    public Section(String name) {
        this.name = name;
        this.tasks = new ArrayList<ITask>();
    }

    @Override
    public String getName() {
        return this.name;
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
}
