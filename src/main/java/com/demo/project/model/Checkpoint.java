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
 * POJO for Task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checkpoint {

    @NotNull
    private Long checkpointId;

    @NotBlank
    private String description;

    @NotEmpty
    @Valid
    private List<Task> tasks;


    private Double completionPercentage;
}
