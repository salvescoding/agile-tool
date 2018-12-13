package io.agilesalves.ppmtool.exceptions;

public class ProjectTaskExceptionResponse {

    private String projectNotFound;

    public ProjectTaskExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
