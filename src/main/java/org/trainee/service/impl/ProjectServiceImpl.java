package org.trainee.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.entity.ProjectEntity;
import org.trainee.exception.TraineeServletException;
import org.trainee.mapper.ProjectDtoMapper;
import org.trainee.repository.ProjectRepository;
import org.trainee.service.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * implements {@link ProjectService}
 *
 * @see ProjectService
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository repository;
    private final ProjectDtoMapper projectDtoMapper;

    public ProjectServiceImpl(ProjectRepository repository, ProjectDtoMapper projectDtoMapper) {
        this.repository = repository;
        this.projectDtoMapper = projectDtoMapper;
    }

    /**
     * {@inheritDoc}
     *
     * @param uuid to find
     * @return {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OutGoingProjectDto findById(UUID uuid) {
        ProjectEntity result = repository.findByIdWithPerformers(uuid)
                .orElseThrow(()-> new TraineeServletException("Project not found"));
        repository.findByIdWithTasks(uuid);
        return projectDtoMapper.map(result);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OutGoingProjectDto> findAll() {
        List<OutGoingProjectDto> projectDtoList = new ArrayList<>();
        List<ProjectEntity> list = repository.findAllWithPerformers();
        if (!list.isEmpty()) {
            repository.findAllWithTasks();
        }
        for (ProjectEntity entity : list) {
            projectDtoList.add(
                    projectDtoMapper.map(entity)
            );
        }
        return projectDtoList;
    }

    /**
     * {@inheritDoc}
     *
     * @param incomingProjectDto to save or update.
     * @return {@inheritDoc}
     */
    @Override
    public OutGoingProjectDto saveOrUpdate(IncomingProjectDto incomingProjectDto) {
        ProjectEntity entity;
        if (incomingProjectDto.getProjectId() == null) {
            incomingProjectDto.setProjectId(UUID.randomUUID());
        }
        entity = repository.save(projectDtoMapper.map(incomingProjectDto));
        return projectDtoMapper.map(entity);
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
        return "Project with uuid: " + uuid + " was deleted";
    }

}
