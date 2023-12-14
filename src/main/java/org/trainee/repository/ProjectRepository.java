package org.trainee.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.trainee.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, UUID> {
    @Query("SELECT pp FROM ProjectEntity pp " +
            "LEFT JOIN FETCH pp.projectPerformers where pp.projectId = :projectId")
    Optional<ProjectEntity> findByIdWithPerformers(@Param("projectId") UUID uuid);

    @Query("SELECT pp FROM ProjectEntity pp " +
            "LEFT JOIN FETCH pp.projectTasks where pp.projectId = :projectId")
    Optional<ProjectEntity> findByIdWithTasks(@Param("projectId") UUID uuid);

    @Query("SELECT distinct pp FROM ProjectEntity pp " +
            "LEFT JOIN FETCH pp.projectPerformers")
    List<ProjectEntity> findAllWithPerformers();

    @Query("SELECT distinct pp FROM ProjectEntity pp " +
            "LEFT JOIN FETCH pp.projectTasks")
    List<ProjectEntity> findAllWithTasks();
}
