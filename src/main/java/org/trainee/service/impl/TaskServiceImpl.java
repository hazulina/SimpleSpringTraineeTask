package org.trainee.service.impl;


import org.springframework.stereotype.Service;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.entity.TaskEntity;
import org.trainee.entity.dictionaries.TaskPriorityEnum;
import org.trainee.entity.dictionaries.TaskStatusEnum;
import org.trainee.exception.TraineeServletException;
import org.trainee.mapper.TaskDtoMapper;
import org.trainee.repository.TaskRepository;
import org.trainee.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * implements {@link TaskService}
 *
 * @see TaskService
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskDtoMapper taskDtoMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskDtoMapper taskDtoMapper) {
        this.taskRepository = taskRepository;
        this.taskDtoMapper = taskDtoMapper;
    }

    /**
     * {@inheritDoc}
     *
     * @param uuid to find
     * @return {@inheritDoc}
     */
    @Override
    public OutGoingTaskDto findById(UUID uuid) {
        return taskDtoMapper.map(
                taskRepository.findByTaskId(uuid)
                        .orElseThrow(()-> new TraineeServletException("Task not found")));
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     */
    @Override
    public List<OutGoingTaskDto> findAll() {
        List<OutGoingTaskDto> taskDtoList = new ArrayList<>();
        for (TaskEntity entity : taskRepository.findAllTasks()) {
            taskDtoList.add(
                    taskDtoMapper.map(entity)
            );
        }
        return taskDtoList;
    }

    /**
     * {@inheritDoc}
     *
     * @param incomingTaskDto to save or update.
     * @return {@inheritDoc}
     */
    @Override
    public OutGoingTaskDto saveOrUpdate(IncomingTaskDto incomingTaskDto) {
        TaskEntity entity;
        incomingTaskDto.setTaskPriority(setValidPriorityOrThrow(incomingTaskDto));
        incomingTaskDto.setTaskStatus(setValidStatusOrThrow(incomingTaskDto));
        if (incomingTaskDto.getTaskId() == null) {
            incomingTaskDto.setTaskId(UUID.randomUUID());
        }
        entity = taskRepository.save(taskDtoMapper.map(incomingTaskDto));
        return taskDtoMapper.map(entity);
    }

    /**
     * {@inheritDoc}
     *
     * @param uuid to delete
     * @return {@inheritDoc}
     */
    @Override
    public String delete(UUID uuid) {
        taskRepository.deleteById(uuid);
        return "Task with uuid: " + uuid + " was deleted";
    }


    /**
     * Checks in dictionary {@link TaskPriorityEnum} if income field is valid.
     *
     * @param incomingTaskDto dto for validation.
     * @return correct value as string or throw an {@link IllegalArgumentException} exception.
     */
    private String setValidPriorityOrThrow(IncomingTaskDto incomingTaskDto) {
        return TaskPriorityEnum.checkValue(incomingTaskDto.getTaskPriority());
    }

    /**
     * Checks in dictionary {@link TaskStatusEnum} if income field is valid.
     *
     * @param incomingTaskDto dto for validation.
     * @return correct value as string or throw an {@link IllegalArgumentException} exception.
     */
    private String setValidStatusOrThrow(IncomingTaskDto incomingTaskDto) {
        return TaskStatusEnum.checkValue(incomingTaskDto.getTaskStatus());
    }


}
