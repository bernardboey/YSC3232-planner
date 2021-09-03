package com.example.lib;

public class Planner implements IPlanner {
    @Override
    public void addBoard(IBoard b) throws AlreadyExistsException {
        // TODO
    }

    @Override
    public void addProject(IProject p) throws AlreadyExistsException {
        // TODO
    }

    @Override
    public Iterable<IBoard> getBoards() {
        // TODO
        return null;
    }

    @Override
    public Iterable<IProject> getProjects() {
        // TODO
        return null;
    }

    @Override
    public String writeXMLData() {
        // TODO
        return null;
    }

    @Override
    public void readXMLData(String data) {
        // TODO
    }
}

class AlreadyExistsException extends Exception {};
class NotFoundException extends Exception {};
