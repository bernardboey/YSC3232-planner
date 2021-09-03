package com.example.lib;

interface IPlanner {
    void addBoard(IBoard b) throws AlreadyExistsException;
    void addProject(IProject p) throws AlreadyExistsException;
    Iterable<IBoard> getBoards();
    Iterable<IProject> getProjects();
    public String writeXMLData();
    /*
    input: raw data from an XML file.
    */
    public void readXMLData(String data);
}
