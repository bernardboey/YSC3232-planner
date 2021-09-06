package com.example.lib;

import java.util.HashSet;

public class Board implements IBoard {
    private final String boardName;
    private final HashSet<ISection> sections;

    public Board(String name) {
        this.boardName = name;
        this.sections = new HashSet<>();
    }

    @Override
    public String getName() {
        return boardName;
    }

    @Override
    public Iterable<ISection> getSections() {
        return sections;
    }

    @Override
    public void addSection(ISection t) throws AlreadyExistsException {
        if (sections.contains(t)) {
            throw new AlreadyExistsException();
        }
        sections.add(t);
    }

    @Override
    public void removeSection(ISection t) throws NotFoundException {
        if (!sections.remove(t)) {
            throw new NotFoundException();
        }
    }

    @Override
    public ISection getSection(String sectionName) throws NotFoundException {
        for (ISection section : sections) {
            if (section.getName().equals(sectionName)) {
                return section;
            }
        }
        throw new NotFoundException();
    }

    public String toXML() {
        StringBuilder xmlData = new StringBuilder("<board name='" + boardName + "'>\n");
        for (ISection section : sections) {
            xmlData.append("\t\t\t").append(((Section)section).toXML());
        }
        xmlData.append("\t\t</board>");
        return xmlData.toString();
    }
}
