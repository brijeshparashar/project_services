package com.demo.project.service;

import com.demo.project.model.ProjectInfo;

public interface ProjectService {
    ProjectInfo getProjectInfo(Long projectId);

    void updateProjectInfo(ProjectInfo projectInfo);
}
