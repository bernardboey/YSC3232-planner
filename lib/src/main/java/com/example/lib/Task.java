package com.example.lib;

import java.time.Duration;

public class Task implements ITask {
    @Override
    public String getName() {
        // TODO
        return null;
    }

    @Override
    public String getDescription() {
        // TODO
        return null;
    }

    @Override
    public Duration getExpectedDuration() {
        // TODO
        return null;
    }

    @Override
    public Iterable<ITask> getSubTasks() {
        // TODO
        return null;
    }

    @Override
    public void addSubTask(ITask t) throws AlreadyExistsException {
        // TODO
    }

    @Override
    public void removeSubTask(ITask t) throws NotFoundException {
        // TODO
    }
}
