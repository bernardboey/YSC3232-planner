package com.example.lib;

public class Board implements IBoard {
    @Override
    public String getName() {
        // TODO
        return null;
    }

    @Override
    public Iterable<ISection> getSections() {
        // TODO
        return null;
    }

    @Override
    public void addSection(ISection t) throws AlreadyExistsException {
        // TODO
    }

    @Override
    public void removeSection(ISection t) throws NotFoundException {
        // TODO
    }

    @Override
    public ISection getSection(String sectionName) throws NotFoundException {
        // TODO
        return null;
    }
}
