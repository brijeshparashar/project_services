package com.demo.project.controller;

import com.demo.project.model.ProjectInfo;
import com.demo.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This is the controller class that exposes Project end points
 */
@RestController
@CrossOrigin
@Validated
@RequestMapping("/project")
public class ProjectController {
    public static final String APPLICATION_JSON = "application/json";
    @Autowired
    private ProjectService projectService;


    /**
     * Retrieves the Project information based on the project id.
     *
     * @param id {@link Long}
     * @return {@link ProjectInfo}
     */
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)

    public ProjectInfo getProject(@PathVariable Long id) {
        return projectService.getProjectInfo(id.longValue());
    }

    /**
     * Updates Project information.
     *
     * @param projectInfo {@link ProjectInfo}
     * @return void
     */

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)

    public void updateProject(
            @RequestBody @Valid ProjectInfo projectInfo) {
        projectService.updateProjectInfo(projectInfo);
    }
}
