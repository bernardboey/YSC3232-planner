package com.example.lib;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.HashSet;

public class Board implements IBoard {
    private final String boardName;
    private final HashSet<ISection> sectionList;

    public Board(String name) {
        this.boardName = name;
        this.sectionList = new HashSet<>();
    }

    @Override
    public String getName() {
        return boardName;
    }

    @Override
    public Iterable<ISection> getSections() {
        return sectionList;
    }

    @Override
    public void addSection(ISection t) throws AlreadyExistsException {
        if (sectionList.contains(t)) {
            throw new AlreadyExistsException();
        }
        else {
            sectionList.add(t);
        }
    }

    @Override
    public void removeSection(ISection t) throws NotFoundException {
        if (sectionList.contains(t)) {
            sectionList.remove(t);
        }
        else {
            throw new NotFoundException();
        }
    }

    @Override
    public ISection getSection(String sectionName) throws NotFoundException {
        for (ISection sectionObj : sectionList) {
            if ((sectionObj.getName()).equals(sectionName)) {
                return sectionObj;
            }
        }
        return null;
    }
}
