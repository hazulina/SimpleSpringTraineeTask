package org.trainee.service;


import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface that represents CRUD operations with Tasks.
 */

public interface TaskService {
    /**
     * Represents a read operation in CRUD for single object.
     *
     * @param uuid to find
     * @return {@link OutGoingTaskDto} to servlet layer.
     */
    OutGoingTaskDto findById(UUID uuid);

    /**
     * Represents a read operation in CRUD for some objects.
     *
     * @return list of {@link OutGoingTaskDto} to servlet layer.
     */
    List<OutGoingTaskDto> findAll();

    /**
     * Represents create or update operations in CRUD.
     *
     * @param incomingTaskDto to save or update.
     * @return result {@link OutGoingTaskDto} to servlet layer.
     */
    OutGoingTaskDto saveOrUpdate(IncomingTaskDto incomingTaskDto);

    /**
     * Represents delete operation in CRUD for single object.
     *
     * @param uuid to delete
     * @return boolean result to servlet layer.
     */
    String delete(UUID uuid);
}
