package org.trainee.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.dictionaries.PerformerRole;
import org.trainee.exception.TraineeServletException;
import org.trainee.mapper.PerformerDtoMapper;
import org.trainee.repository.PerformerRepository;
import org.trainee.service.PerformerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * implements {@link PerformerService}
 *
 * @see PerformerService
 */
@Service
public class PerformerServiceImpl implements PerformerService {
    private final PerformerRepository repository;
    private final PerformerDtoMapper dtoMapper;

    public PerformerServiceImpl(PerformerRepository repository, PerformerDtoMapper dtoMapper) {
        this.repository = repository;
        this.dtoMapper = dtoMapper;
    }

    /**
     * {@inheritDoc}
     *
     * @param uuid to find
     * @return {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OutGoingPerformerDto findById(UUID uuid) {
        PerformerEntity result = repository.findByIdWithProject(uuid)
                .orElseThrow(()-> new TraineeServletException("Performer not found")) ;
        repository.findByIdWithTask(uuid);
        return dtoMapper.map(result);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OutGoingPerformerDto> findAll() {
        List<OutGoingPerformerDto> performerDtoList = new ArrayList<>();
        List<PerformerEntity> list = repository.findAllWithProjects();
        if (!list.isEmpty()) {
            repository.findAllWithTasks();
        }
        for (PerformerEntity entity : list) {
            performerDtoList.add(
                    dtoMapper.map(entity)
            );
        }
        return performerDtoList;
    }

    /**
     * {@inheritDoc}
     *
     * @param incomingPerformerDto to save or update.
     * @return {@inheritDoc}
     */
    @Override
    public OutGoingPerformerDto saveOrUpdate(IncomingPerformerDto incomingPerformerDto) {
        PerformerEntity entity;
        incomingPerformerDto.setRole(setValidRoleOrThrow(incomingPerformerDto.getRole()));
        if (incomingPerformerDto.getPerformerId() == null) {
            incomingPerformerDto.setPerformerId(UUID.randomUUID());
        }
        entity = repository.save(dtoMapper.map(incomingPerformerDto));
        return dtoMapper.map(entity);
    }

    /**
     * Checks in dictionary {@link PerformerRole} if income field is valid.
     *
     * @param inputRole string for validation.
     * @return correct value as string or throw an {@link IllegalArgumentException} exception.
     */
    private String setValidRoleOrThrow(String inputRole) {
        return PerformerRole.checkValue(inputRole);
    }

    /**
     * {@inheritDoc}
     *
     * @param uuid to delete
     * @return {@inheritDoc}
     */
    @Override
    public String delete(UUID uuid) {
        repository.deleteById(uuid);
        return "Performer with uuid: " + uuid + " was deleted";
    }


}
