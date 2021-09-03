package com.example.lib;

public class Project implements IProject {
    @Override
    public String getName() {
        // TODO
        return null;
    }

    @Override
    public Iterable<ITask> getTasks() {
        // TODO
        return null;
    }

    @Override
    public void addTask(ITask t) throws AlreadyExistsException {
        // TODO
    }

    @Override
    public void removeTask(ITask t) throws NotFoundException {
        // TODO
    }
}
