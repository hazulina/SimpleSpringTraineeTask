package org.trainee.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.trainee.entity.TaskEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, UUID> {
    @Query("SELECT t FROM TaskEntity t " +
            "LEFT JOIN FETCH t.taskPerformer p " +
            "LEFT JOIN FETCH t.taskProject pp where t.taskId = :taskId")
    Optional<TaskEntity> findByTaskId(@Param("taskId")UUID uuid);


    @Query("SELECT e FROM TaskEntity e LEFT JOIN FETCH e.taskPerformer t LEFT JOIN FETCH e.taskProject")
    Iterable<TaskEntity> findAllTasks();
}
