package com.demo.project.persistence.repository;

import com.demo.project.persistence.entity.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to perform Crud operations on ProjectDetails table.
 */
@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
}
