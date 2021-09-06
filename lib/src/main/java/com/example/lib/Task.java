package com.example.lib;

import java.time.Duration;
import java.util.HashSet;

public class Task implements ITask {
    private final String name;
    private final String description;
    private final Duration expectedDuration;
    private final HashSet<ITask> subTasks;

    public Task(String name, String description, Duration expectedDuration) {
        this.name = name;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.subTasks = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Duration getExpectedDuration() {
        return expectedDuration;
    }

    @Override
    public Iterable<ITask> getSubTasks() {
        return subTasks;
    }

    @Override
    public void addSubTask(ITask t) throws AlreadyExistsException {
        if (subTasks.contains(t)) {
            throw new AlreadyExistsException();
        }
        subTasks.add(t);
    }

    @Override
    public void removeSubTask(ITask t) throws NotFoundException {
        if (!subTasks.remove(t)) {
            throw new NotFoundException();
        }
    }

    public String toXML() {
        StringBuilder taskXML = new StringBuilder(
                String.format("\t\t\t<task name='%s' description='%s' expected-duration='%s'>\n",
                        name, description, expectedDuration)
        );

        taskXML.append("\t\t\t\t<subtasks>\n");
        for (ITask t : subTasks) {
            taskXML.append(((Task) t).toXML());
        }
        taskXML.append("\t\t\t\t</subtasks>\n");

        taskXML.append("\t\t\t</task>\n");
        return taskXML.toString();
    }
}
