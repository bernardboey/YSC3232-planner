package com.example.lib;

import java.time.Duration;

interface ITask {
    String getName();
    String getDescription() ;
    Duration getExpectedDuration();
    Iterable<ITask> getSubTasks() ;
    void addSubTask(ITask t) throws AlreadyExistsException;
    void removeSubTask(ITask t) throws NotFoundException;
    String toXML(int indentationLevel);
}
