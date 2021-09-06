package com.example.lib;

import java.time.Duration;
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

    public String toXML() {
        StringBuilder taskXML = new StringBuilder(String.format("<section name='%s'>\n", name));
        for (ITask t : tasks)
            taskXML.append("\t" + (((Task) t).toXML()) + "\n");
        return taskXML + "\t\t\t</section>\n";
    }

    public static void main(String[] args) {
        Section testSection = new Section("testSection");
        Task testTask = new Task("testTask", "this is a test task", Duration.ofHours(1));
        try {
            testSection.addTask(testTask);
        } catch (AlreadyExistsException e) {
            e.printStackTrace();
        }
        System.out.print(testSection.toXML());
    }
}
