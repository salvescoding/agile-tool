package io.agilesalves.ppmtool.repositories;

import io.agilesalves.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String projectIdentifier);

    ProjectTask findByProjectSequence(String projectSequence);

}
