package com.demo.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * POJO for Task.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @NotNull
    private Long taskId;
    @NotBlank
    private String taskDescription;
    @NotNull
    private boolean taskCompleted;
}