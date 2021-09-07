package com.example.lib;

import java.util.ArrayList;

public class Board implements IBoard {
    private final String name;
    private final ArrayList<ISection> sections;

    public Board(String name) {
        this.name = name;
        this.sections = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
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

    @Override
    public String toXML(int indentationLevel) {
        String indentation = "\t".repeat(indentationLevel);

        StringBuilder boardXML = new StringBuilder(
                String.format("%s<board name='%s'>\n", indentation, name)
        );

        boardXML.append(indentation).append("\t<sections>\n");
        for (ISection s : sections) {
            boardXML.append(s.toXML(indentationLevel + 2));
        }
        boardXML.append(indentation).append("\t</sections>\n");

        boardXML.append(indentation).append("</board>\n");
        return boardXML.toString();
    }
}
