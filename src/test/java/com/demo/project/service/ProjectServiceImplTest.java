package com.demo.project.service;

import com.demo.project.exception.ProjectInputException;
import com.demo.project.exception.ProjectNotFoundException;
import com.demo.project.model.Checkpoint;
import com.demo.project.model.ProjectInfo;
import com.demo.project.model.Task;
import com.demo.project.persistence.entity.CheckpointEntity;
import com.demo.project.persistence.entity.ProjectEntity;
import com.demo.project.persistence.entity.TaskEntity;
import com.demo.project.persistence.repository.ProjectDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {

    @Mock
    private ProjectDAO projectDAO;

    @InjectMocks
    private ProjectServiceImpl projectService;


    @Test
    public void whenGetProject_AndProjectPresentInDb_ThenProjectReturnedSuccessfully() {
        Long projectId = Long.valueOf("1");
        Mockito.when(projectDAO.getProjectInfo(projectId))
                .thenReturn(Optional.of(mockProjectEntity()));
        ProjectInfo projectInfo = projectService.getProjectInfo(projectId);
        Assert.assertNotNull(projectInfo);
        Assert.assertEquals( "Project Progress Tracking Tool",projectInfo.getProjectName());
    }

    @Test
    public void whenGetProject_ThenAssertCompletionPercentage() {
        Long projectId = Long.valueOf("1");
        Mockito.when(projectDAO.getProjectInfo(projectId))
                .thenReturn(Optional.of(mockProjectEntity()));
        ProjectInfo projectInfo = projectService.getProjectInfo(projectId);
        Assert.assertNotNull(projectInfo);
        Assert.assertEquals(Optional.of(100.00d),Optional.of(projectInfo.getCheckpoints().get(0).getCompletionPercentage()));
    }

    @Test
    public void whenGetProject_AndProjectPresentInDb_ThenAssertPercentageCompletionForCheckPoint1() {
        Long projectId = Long.valueOf("1");
        Mockito.when(projectDAO.getProjectInfo(projectId))
                .thenReturn(Optional.of(mockProjectEntity()));
        ProjectInfo projectInfo = projectService.getProjectInfo(projectId);
        Assert.assertNotNull(projectInfo);
        Assert.assertEquals(Optional.ofNullable(projectInfo.getCheckpoints().get(0).getCompletionPercentage()), Optional.ofNullable(100.0));
    }

    @Test
    public void whenGetProject_AndProjectNotInDb_ThenRecordNotFoundException() {
        Long projectId = Long.valueOf("2");
        Mockito.when(projectDAO.getProjectInfo(projectId))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ProjectNotFoundException.class, () -> projectService.getProjectInfo(projectId));
    }





    private ProjectEntity mockProjectEntity() {
        return ProjectEntity.builder()
                .projectId(Long.valueOf("1"))
                .projectName("Project Progress Tracking Tool")
                .checkpoints(mockCheckpointEntities())
                .build();
    }

    private List<CheckpointEntity> mockCheckpointEntities() {
        List<CheckpointEntity> checkpoints = new ArrayList<>();
        checkpoints.add(CheckpointEntity.builder()
                .checkpointId(Long.valueOf("1001"))
                .description("Checkpoint: RFP Approval")
                .tasks(mockTaskEntitiesForCheckpoint1())
                .build());
        checkpoints.add(CheckpointEntity.builder()
                .checkpointId(Long.valueOf("1002"))
                .description("CheckPoint: Pre-requisites for starting a project")
                .tasks(mockTaskEntitiesForCheckpoint2())
                .build());
        return checkpoints;
    }

    private List<TaskEntity> mockTaskEntitiesForCheckpoint1() {
        List<TaskEntity> tasks = new ArrayList<>();
        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("101"))
                .taskDescription("Task : Requirement Gathering")
                .taskCompleted(true)
                .build());
        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("102"))
                .taskDescription("Task : Detailed Analysis")
                .taskCompleted(true)
                .build());

        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("103"))
                .taskDescription("Task : Final approval from client")
                .taskCompleted(true)
                .build());
        return tasks;
    }

    private List<TaskEntity> mockTaskEntitiesForCheckpoint2() {
        List<TaskEntity> tasks = new ArrayList<>();
        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("104"))
                .taskDescription("Task : Budget Approval from Management")
                .taskCompleted(true)
                .build());
        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("105"))
                .taskDescription("Task : Procuring Infrastructure & Hardware")
                .taskCompleted(true)
                .build());

        tasks.add(TaskEntity.builder()
                .taskId(Long.valueOf("106"))
                .taskDescription("Task : Hiring Resources")
                .taskCompleted(true)
                .build());
        return tasks;
    }
}