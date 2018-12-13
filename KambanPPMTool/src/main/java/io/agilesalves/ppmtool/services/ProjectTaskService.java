package io.agilesalves.ppmtool.services;


import io.agilesalves.ppmtool.domain.Backlog;
import io.agilesalves.ppmtool.domain.BacklogStatus;
import io.agilesalves.ppmtool.domain.ProjectTask;
import io.agilesalves.ppmtool.exceptions.ProjectNotFoundException;
import io.agilesalves.ppmtool.repositories.BacklogRepository;
import io.agilesalves.ppmtool.repositories.ProjectRepository;
import io.agilesalves.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
            projectTask.setBacklog(backlog);
            Integer backlogSequence = setBacklogPTSequence(backlog);
            setProjectSequenceAndProjectIdentifier(projectIdentifier, projectTask, backlogSequence);
            setProjectTaskPriority(projectTask);
            setProjectTaskStatus(projectTask);
            projectTaskRepository.save(projectTask);
            return projectTask;
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found" );
        }
    }

    public Iterable<ProjectTask> getAllProjectTasks(String projectIdentifier) {
        if (projectRepository.findByProjectIdentifier(projectIdentifier) == null) {
            throw new ProjectNotFoundException("The project with ID '"+projectIdentifier+"' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectIdentifier);
    }

    private Integer setBacklogPTSequence(Backlog backlog) {
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        return backlogSequence;
    }

    private void setProjectTaskStatus(ProjectTask projectTask) {
        if (projectTask.getStatus() == null) {
            projectTask.setStatus(BacklogStatus.TODO);
        }
    }

    private void setProjectTaskPriority(ProjectTask projectTask) {
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
    }

    private void setProjectSequenceAndProjectIdentifier(String projectIdentifier, ProjectTask projectTask, Integer backlogSequence) {
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
    }


}
