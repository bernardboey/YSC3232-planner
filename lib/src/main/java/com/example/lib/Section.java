package com.example.lib;

import java.time.Duration;
import java.util.ArrayList;

public class Section implements ISection {
    private final String name;
    private final ArrayList<ITask> tasks;

    public Section(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
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

    @Override
    public String toXML(int indentationLevel) {
        String indentation = "\t".repeat(indentationLevel);

        StringBuilder sectionXML = new StringBuilder(
                String.format("%s<section name='%s'>\n", indentation, name)
        );

        sectionXML.append(indentation).append("\t<tasks>\n");
        for (ITask t : tasks) {
            sectionXML.append(t.toXML(indentationLevel + 2));
        }
        sectionXML.append(indentation).append("\t</tasks>\n");

        sectionXML.append(indentation).append("</section>\n");
        return sectionXML.toString();
    }

    public static void main(String[] args) {
        Section testSection = new Section("testSection");
        Task testTask = new Task("testTask", "this is a test task", Duration.ofHours(1));
        try {
            testSection.addTask(testTask);
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        System.out.print(testSection.toXML(0));
    }
}
