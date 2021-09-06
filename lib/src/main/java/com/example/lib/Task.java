package com.example.lib;

import java.time.Duration;
import java.util.ArrayList;

public class Task implements ITask {
    private final String name;
    private final String description;
    private final Duration expectedDuration;
    private final ArrayList<ITask> subTasks;

    public Task(String name, String description, Duration expectedDuration) {
        this.name = name;
        this.description = description;
        this.expectedDuration = expectedDuration;
        this.subTasks = new ArrayList<>();
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

    public String toXML(int indentationLevel) {
        String indentation = "\t".repeat(indentationLevel);

        StringBuilder taskXML = new StringBuilder(
                String.format("%s<task name='%s' description='%s' expected-duration='%s'>\n",
                        indentation, name, description, expectedDuration)
        );

        taskXML.append(indentation).append("\t<subtasks>\n");
        for (ITask t : subTasks) {
            taskXML.append(((Task) t).toXML(indentationLevel + 2));
        }
        taskXML.append(indentation).append("\t</subtasks>\n");

        taskXML.append(indentation).append("</task>\n");
        return taskXML.toString();
    }
}
