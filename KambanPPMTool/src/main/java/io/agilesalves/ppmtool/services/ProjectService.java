package io.agilesalves.ppmtool.services;

import io.agilesalves.ppmtool.domain.Backlog;
import io.agilesalves.ppmtool.domain.Project;
import io.agilesalves.ppmtool.exceptions.ProjectIdException;
import io.agilesalves.ppmtool.repositories.BacklogRepository;
import io.agilesalves.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public Project saveOrUpdate(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            createRelationshipProjectWithBacklog(project);
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

    private void createRelationshipProjectWithBacklog(Project project) {
        if (project.getId() == null) {
            Backlog backlog = new Backlog();
            project.setBacklog(backlog);
            backlog.setProject(project);
            backlog.setProjectIdentifier(project.getProjectIdentifier());
        } else {
            Backlog backlog = backlogRepository.findByProjectIdentifier(project.getProjectIdentifier());
            project.setBacklog(backlog);
        }
    }
}
