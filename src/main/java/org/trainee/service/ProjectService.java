package org.trainee.service;


import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface that represents CRUD operations with Projects.
 */

public interface ProjectService {
    /**
     * Represents a read operation in CRUD for single object.
     *
     * @param uuid to find
     * @return {@link OutGoingProjectDto} to servlet layer.
     */
    OutGoingProjectDto findById(UUID uuid);

    /**
     * Represents a read operation in CRUD for some objects.
     *
     * @return list of {@link OutGoingProjectDto} to servlet layer.
     */
    List<OutGoingProjectDto> findAll();

    /**
     * Represents create or update operations in CRUD.
     *
     * @param incomingProjectDto to save or update.
     * @return result {@link OutGoingProjectDto} to servlet layer.
     */
    OutGoingProjectDto saveOrUpdate(IncomingProjectDto incomingProjectDto);

    /**
     * Represents delete operation in CRUD for single object.
     *
     * @param uuid to delete
     * @return boolean result to servlet layer.
     */
    String delete(UUID uuid);
}
