package com.demo.project.persistence.repository;

import com.demo.project.persistence.entity.CheckpointEntity;
import com.demo.project.persistence.entity.ProjectEntity;
import com.demo.project.persistence.entity.TaskEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test class to validate Repository methods by loading the actual DB objects.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    public void whenGetById_thenReturnProject() {
        Long projectId = Long.valueOf("1");
        Optional<ProjectEntity> projectEntity = projectRepository.findById(projectId);
        assertNotNull(projectEntity.get());
        assertEquals("Project Progress Tracking Tool",projectEntity.get().getProjectName() );
    }

    @Test
    public void whenGetById_WithNoRecordInDB_thenReturnOptionalEmpty() {
        Long userId = Long.valueOf(2);
        Optional<ProjectEntity> projectEntity = projectRepository.findById(userId);
        assertFalse(projectEntity.isPresent());
    }

    @Test
    public void whenUpdateProject_thenReturnUpdatedProject() {
        ProjectEntity projectEntity = projectRepository.save(mockProjectEntity());
        assertNotNull(projectEntity);
        assertEquals("Project Progress Tracking Tool", projectEntity.getProjectName());
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

}