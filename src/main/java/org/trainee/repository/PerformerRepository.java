package org.trainee.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.trainee.entity.PerformerEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PerformerRepository extends CrudRepository<PerformerEntity, UUID> {
    @Query("SELECT p FROM PerformerEntity p " +
            "LEFT JOIN FETCH p.performerTasks t  where p.performerId = :performerId")
    Optional<PerformerEntity> findByIdWithTask(@Param("performerId") UUID uuid);

    @Query("SELECT p FROM PerformerEntity p " +
            "LEFT JOIN FETCH p.performerProjects t  where p.performerId = :performerId")
    Optional<PerformerEntity> findByIdWithProject(@Param("performerId") UUID uuid);

    @Query("SELECT distinct p FROM PerformerEntity p " +
            "LEFT JOIN FETCH p.performerProjects")
    List<PerformerEntity> findAllWithProjects();

    @Query("SELECT distinct p FROM PerformerEntity p " +
            "LEFT JOIN FETCH p.performerTasks")
    List<PerformerEntity> findAllWithTasks();
}
