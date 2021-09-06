package com.example.lib;

import java.util.HashMap;

public class Planner implements IPlanner {
    final HashMap<String, IBoard> boards;
    final HashMap<String, IProject> projects;

    public Planner() {
        this.boards = new HashMap<>();
        this.projects = new HashMap<>();
    }

    @Override
    public void addBoard(IBoard b) throws AlreadyExistsException {
        if (boards.containsKey(b.getName())) {
            throw new AlreadyExistsException();
        }
        boards.put(b.getName(), b);
    }

    @Override
    public void addProject(IProject p) throws AlreadyExistsException {
        if (boards.containsKey(p.getName())) {
            throw new AlreadyExistsException();
        }
        boards.put(p.getName(), p);
    }

    @Override
    public Iterable<IBoard> getBoards() {
        return boards.values();
    }

    @Override
    public Iterable<IProject> getProjects() {
        return projects.values();
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

class AlreadyExistsException extends Exception {}
class NotFoundException extends Exception {}
