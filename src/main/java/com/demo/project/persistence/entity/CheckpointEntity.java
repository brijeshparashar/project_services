package com.demo.project.persistence.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO for Task.
 */
@Entity
@Table(name = "Checkpoint")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class CheckpointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long checkpointId;

    private String description;

    @ManyToOne
    @JoinColumn(name = "projectId", nullable = false)
    private ProjectEntity project;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "checkpoint")
    private List<TaskEntity> tasks = new ArrayList<>();
}
