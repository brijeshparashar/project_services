package com.demo.project.service;

import com.demo.project.exception.ProjectNotFoundException;
import com.demo.project.mapper.ProjectMapper;
import com.demo.project.model.ProjectInfo;
import com.demo.project.persistence.entity.ProjectEntity;
import com.demo.project.persistence.repository.ProjectDAO;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Service class provides business logic implementation for different project details Api operations.
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    private ProjectMapper projectMapper = Mappers.getMapper(ProjectMapper.class);


    @Override
    public ProjectInfo getProjectInfo(Long projectId) {
        ProjectEntity projectEntity = projectDAO.getProjectInfo(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found in DB."));
        return ProjectInfo.builder()
                .projectId(projectEntity.getProjectId())
                .projectName(projectEntity.getProjectName())
                .checkpoints(projectEntity.getCheckpoints()
                        .stream()
                        .map(c -> projectMapper.mapCheckpointEntityToCheckpoint(c))
                        .collect(Collectors.toList()))
                .build();
    }


    @Override
    public void updateProjectInfo(ProjectInfo projectInfo) {
        projectDAO.updateProject(projectMapper.mapProjectInfoToProjectEntity(projectInfo));
    }
}
