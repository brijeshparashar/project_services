package com.demo.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * POJO for Project details.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo {

    @NotNull
    private Long projectId;
    @NotBlank
    private String projectName;
    @NotEmpty
    @Valid
    List<Checkpoint> checkpoints;
}
