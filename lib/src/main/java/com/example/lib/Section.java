    package com.example.lib;

    import java.util.HashSet;

    public class Section implements ISection {
    private String name;
    private HashSet<ITask> taskList;

    public Section(String name) {
        this.name = name;
    };

    public Section(String name, HashSet<ITask> tasks) {
        this.name = name;
        this.taskList = tasks;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Iterable<ITask> getTasks() {
        return taskList;
    }

    @Override
    public void addTask(ITask t) throws AlreadyExistsException {
        if (taskList.contains(t))
            throw new AlreadyExistsException();
        taskList.add(t);
    }

    @Override
    public void removeTask(ITask t) throws NotFoundException {
        if (!taskList.contains(t))
            throw new NotFoundException();
        taskList.remove(t);
    }
}
