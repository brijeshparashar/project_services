package com.demo.project.controller;

import com.demo.project.ProjectApplication;
import com.demo.project.model.Checkpoint;
import com.demo.project.model.ErrorResponse;
import com.demo.project.model.ProjectInfo;
import com.demo.project.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = ProjectApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetProject_AndProjectPresentInDb_thenReturnProjectJsonObject() throws Exception {
        HttpEntity request = new HttpEntity(getHeaders());
        ResponseEntity<ProjectInfo> response = restTemplate.exchange(
                "http://localhost:" + port + "/project/1",
                HttpMethod.GET,
                request,
                ProjectInfo.class
        );
        assertEquals("Project Progress Tracking Tool", response.getBody().getProjectName());
    }


    @Test
    public void whenGetProject_AndProjectNOTPresentInDb_thenReturnProjectJsonObject() throws Exception {
        HttpEntity request = new HttpEntity(getHeaders());
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/project/2",
                HttpMethod.GET,
                request,
                ErrorResponse.class
        );
        assertEquals("ER001", response.getBody().getErrorCode());
    }


    @Test
    public void whenUpdateProject_thenProjectShouldBeUpdatedSuccessfully() throws Exception {
        HttpEntity<ProjectInfo> request = new HttpEntity(mockProject(), getHeaders());
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/project",
                HttpMethod.POST,
                request,
                ErrorResponse.class
        );
        assertNull(response.getBody());
    }

    @Test
    public void whenUpdateProject_AndPassingRequiredFieldAsNULL_thenReturnErrorCodeER002() throws Exception {
        ProjectInfo projectInfo = mockProject();
        projectInfo.setProjectName(null);
        HttpEntity<ProjectInfo> request = new HttpEntity(projectInfo, getHeaders());
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/project",
                HttpMethod.POST,
                request,
                ErrorResponse.class
        );
        assertEquals("ER002", response.getBody().getErrorCode());
    }

    @Test
    public void whenUpdateProject_AndPassingRandomCheckpointId_thenDBExceptionReturnErrorCodeER003() throws Exception {
        ProjectInfo projectInfo = mockProject();
        projectInfo.getCheckpoints().get(0).setCheckpointId(Long.valueOf("1234"));
        HttpEntity<ProjectInfo> request = new HttpEntity(projectInfo, getHeaders());
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(
                "http://localhost:" + port + "/project",
                HttpMethod.POST,
                request,
                ErrorResponse.class
        );
        assertEquals("ER003", response.getBody().getErrorCode());
    }


    private ProjectInfo mockProject() {
        return ProjectInfo.builder()
                .projectId(Long.valueOf("1"))
                .projectName("Project Progress Tracking Tool")
                .checkpoints(mockCheckpoints())
                .build();
    }

    private List<Checkpoint> mockCheckpoints() {
        List<Checkpoint> checkpoints = new ArrayList<>();
        checkpoints.add(Checkpoint.builder()
                .checkpointId(Long.valueOf("1001"))
                .description("Checkpoint: RFP Approval")
                .tasks(mockTasksForCheckpoint1())
                .build());
        checkpoints.add(Checkpoint.builder()
                .checkpointId(Long.valueOf("1002"))
                .description("CheckPoint: Pre-requisites for starting a project")
                .tasks(mockTasksForCheckpoint2())
                .build());
        return checkpoints;
    }

    private List<Task> mockTasksForCheckpoint1() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder()
                .taskId(Long.valueOf("101"))
                .taskDescription("Task : Requirement Gathering")
                .taskCompleted(true)
                .build());
        tasks.add(Task.builder()
                .taskId(Long.valueOf("102"))
                .taskDescription("Task : Detailed Analysis")
                .taskCompleted(true)
                .build());

        tasks.add(Task.builder()
                .taskId(Long.valueOf("103"))
                .taskDescription("Task : Final approval from client")
                .taskCompleted(false)
                .build());
        return tasks;
    }

    private List<Task> mockTasksForCheckpoint2() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(Task.builder()
                .taskId(Long.valueOf("104"))
                .taskDescription("Task : Budget Approval from Management")
                .taskCompleted(true)
                .build());
        tasks.add(Task.builder()
                .taskId(Long.valueOf("105"))
                .taskDescription("Task : Procuring Infrastructure & Hardware")
                .taskCompleted(false)
                .build());

        tasks.add(Task.builder()
                .taskId(Long.valueOf("106"))
                .taskDescription("Task : Hiring Resources")
                .taskCompleted(false)
                .build());
        return tasks;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }
}