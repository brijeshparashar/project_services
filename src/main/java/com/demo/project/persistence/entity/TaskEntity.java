package com.demo.project.persistence.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * POJO for Task.
 */
@Entity
@Table(name= "Task")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class TaskEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long taskId;
    private String taskDescription;
    private boolean taskCompleted;
    @ManyToOne
    @JoinColumn(name= "checkpointId", nullable = false)
    private CheckpointEntity checkpoint;

}