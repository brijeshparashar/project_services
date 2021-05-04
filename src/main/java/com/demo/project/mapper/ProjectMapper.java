package com.demo.project.mapper;

import com.demo.project.persistence.entity.*;
import com.demo.project.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.text.DecimalFormat;

/**
 * Mapper class to map ProjectDetails object to ProjectEntity objects.
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper {

    static DecimalFormat df = new DecimalFormat("###.##");

    ProjectEntity mapProjectInfoToProjectEntity(ProjectInfo projectInfo);

    @Mapping(target = "completionPercentage", expression = "java(getCheckPointCompletionPercentage(checkpointEntity))")
    Checkpoint mapCheckpointEntityToCheckpoint(CheckpointEntity checkpointEntity);

    default double getCheckPointCompletionPercentage(CheckpointEntity checkpointEntity) {
        double completedCount = checkpointEntity
                .getTasks()
                .stream()
                .filter(t -> t.isTaskCompleted())
                .count();
        return Double.parseDouble(df.format(completedCount / checkpointEntity.getTasks().size() * 100.0f));
    }
}
