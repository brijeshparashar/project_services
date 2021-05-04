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
@Table(name= "Project")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;

    private String projectName;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "project")
    private List<CheckpointEntity> checkpoints = new ArrayList<>();
}
