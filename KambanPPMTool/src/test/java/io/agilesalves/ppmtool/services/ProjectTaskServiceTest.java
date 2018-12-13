package io.agilesalves.ppmtool.services;

import io.agilesalves.ppmtool.domain.Project;
import io.agilesalves.ppmtool.domain.ProjectTask;
import io.agilesalves.ppmtool.exceptions.ProjectSequenceException;
import io.agilesalves.ppmtool.repositories.ProjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProjectTaskServiceTest {



    private ProjectTaskService projectTaskService;
    private ProjectTask projectTask;

    @Before
    public void setUp() throws Exception {
        projectTaskService = new ProjectTaskService();
        projectTask = new ProjectTask();
    }

    @Test
    public void shouldThrowExceptionWhenProjectSequenceDoesNotExist() {
        projectTask.setProjectSequence("ID01-1");
        assertThrows(ProjectSequenceException.class, () -> projectTaskService.findByProjectSequence("ID01-0"));
    }

}
