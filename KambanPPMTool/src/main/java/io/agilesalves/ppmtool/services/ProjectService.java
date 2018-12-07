package io.agilesalves.ppmtool.services;

import io.agilesalves.ppmtool.domain.Project;
import io.agilesalves.ppmtool.exceptions.ProjectIdException;
import io.agilesalves.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists!" );
        }
    }

    public Project findByProjectIdentifier(String projectIdentifier) {
        Project project =  projectRepository.findByProjectIdentifier(projectIdentifier);
        if (project == null) {
            throw new ProjectIdException("Project ID " + projectIdentifier + " does not exist" );
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectById(String projectId) {
        Project project = this.findByProjectIdentifier(projectId);
        if (project == null) throw new ProjectIdException("Could not find project with ID: " + projectId);
        projectRepository.delete(project);
    }
}
