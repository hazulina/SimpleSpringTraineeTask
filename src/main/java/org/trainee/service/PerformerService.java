package org.trainee.service;


import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;

import java.util.List;
import java.util.UUID;

/**
 * Interface that represents CRUD operations with Performers.
 */

public interface PerformerService {
    /**
     * Represents a read operation in CRUD for single object.
     *
     * @param uuid to find
     * @return {@link OutGoingPerformerDto} to servlet layer.
     */
    OutGoingPerformerDto findById(UUID uuid);

    /**
     * Represents a read operation in CRUD for some objects.
     *
     * @return list of {@link OutGoingPerformerDto} to servlet layer.
     */
    List<OutGoingPerformerDto> findAll();

    /**
     * Represents create or update operations in CRUD.
     *
     * @param incomingPerformerDto to save or update.
     * @return result {@link OutGoingPerformerDto} to servlet layer.
     */
    OutGoingPerformerDto saveOrUpdate(IncomingPerformerDto incomingPerformerDto);

    /**
     * Represents delete operation in CRUD for single object.
     *
     * @param uuid to delete
     * @return boolean result to servlet layer.
     */
    String delete(UUID uuid);
}
