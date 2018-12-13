package io.agilesalves.ppmtool.services;


import io.agilesalves.ppmtool.domain.Backlog;
import io.agilesalves.ppmtool.domain.BacklogStatus;
import io.agilesalves.ppmtool.domain.ProjectTask;
import io.agilesalves.ppmtool.repositories.BacklogRepository;
import io.agilesalves.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
        projectTask.setBacklog(backlog);
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        projectTask.setProjectSequence(projectIdentifier+"-"+backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        if (projectTask.getPriority() == null) {
            projectTask.setPriority(3);
        }
        if (projectTask.getStatus() == null) {
            projectTask.setStatus(BacklogStatus.TODO);
        }
        projectTaskRepository.save(projectTask);
        return projectTask;
    }
}
