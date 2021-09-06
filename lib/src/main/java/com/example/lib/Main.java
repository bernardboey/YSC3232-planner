package com.example.lib;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws AlreadyExistsException {
        Planner planner = new Planner();
        Project project = new Project("YSC3232");
        planner.addProject(project);
        Task T1 = new Task("T1", "Task 1", Duration.ofHours(1));
        Task T2 = new Task("T2", "Task 2", Duration.ofHours(1));
        Task T3 = new Task("T3", "Task 3", Duration.ofHours(1));
        project.addTask(T1);
        project.addTask(T2);
        project.addTask(T3);
        Board board = new Board("YSC3232");
        planner.addBoard(board);
        Section todoSection = new Section("TODO");
        Section doneSection = new Section("Done");
        board.addSection(todoSection);
        board.addSection(doneSection);
        todoSection.addTask(T1);
        todoSection.addTask(T2);
        doneSection.addTask(T3);
        String xmlData = planner.writeXMLData();
        System.out.println(xmlData);
    }
}
