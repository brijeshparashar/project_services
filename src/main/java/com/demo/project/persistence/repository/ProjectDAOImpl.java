package com.demo.project.persistence.repository;

import com.demo.project.persistence.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository layer abstracting Spring Data JPA CRUD functions with custom implementations.
 */
@Repository
public class ProjectDAOImpl implements ProjectDAO {


    @Autowired
    private ProjectRepository projectRepository;


    /**
     * Method to update ProjectDetails into Database.
     *
     * @param projectEntity {@link ProjectEntity}
     * @return {@link ProjectEntity}
     */
    @Override
    public ProjectEntity updateProject(ProjectEntity projectEntity) {
        projectEntity.getCheckpoints().forEach(checkpoint -> {
            checkpoint.setProject(projectEntity);
            checkpoint.getTasks().forEach(task -> task.setCheckpoint(checkpoint));
        });
        return projectRepository.save(projectEntity);
    }


    @Override
    public Optional<ProjectEntity> getProjectInfo(Long projectId) {
        return projectRepository.findById(projectId);
    }

}
