package com.demo.project.persistence.repository;

import com.demo.project.persistence.entity.ProjectEntity;

import java.util.Optional;

public interface ProjectDAO {
    Optional<ProjectEntity> getProjectInfo(Long projectId);
    ProjectEntity updateProject(ProjectEntity projectEntity);
}
