package org.trainee.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;
import org.trainee.mapper.TaskDtoMapper;
import org.trainee.mapper.TaskDtoMapperImpl;
import org.trainee.repository.TaskRepository;
import org.trainee.service.TaskService;
import org.trainee.service.impl.TaskServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskServiceTest {
    private final TaskRepository repository = Mockito.mock(TaskRepository.class);
    private final TaskDtoMapper dtoMapper = Mockito.spy(TaskDtoMapperImpl.class);

    @InjectMocks
    TaskService service = new TaskServiceImpl(repository, dtoMapper);

    @Test
    void should_find_By_Id_and_return_TaskDto_CorrectTest() {
        //given
        UUID testId = UUID.randomUUID();
        Optional<TaskEntity> test = createEntity();
        when(repository.findByTaskId(any())).thenReturn(test);
        OutGoingTaskDto given = dtoMapper.map(test.orElseThrow());

        //when
        OutGoingTaskDto result = service.findById(testId);

        //then
        assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(OutGoingTaskDto.class)
                .isEqualTo(given);
    }

    @Test
    void should_findAll_and_return_List_of_TaskDto() {

        //Given
        when(repository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<OutGoingTaskDto> actual = service.findAll();

        //Then
        assertThat(actual).isNotNull();

    }


    @Test
    void should_save_and_return_TaskDto() {
        //Given
        UUID uuid = UUID.randomUUID();
        ProjectEntity test1 = new ProjectEntity();
        test1.setProjectId(UUID.randomUUID());
        PerformerEntity test2 = new PerformerEntity();
        test2.setPerformerId(UUID.randomUUID());

        IncomingTaskDto given = new IncomingTaskDto();
        given.setTaskStatus("at WOrk");
        given.setTaskPriority("low");
        TaskEntity mockEntity = new TaskEntity(
                uuid,
                "firstTask",
                "cool task",
                "LOW",
                "AT WORK",
                Timestamp.valueOf("2023-11-20 12:34:56"),
                test2,
                test1);

        when(repository.save(any())).thenReturn(mockEntity);

        //When
        OutGoingTaskDto actual = service.saveOrUpdate(given);
        //Then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("taskStatus", "AT WORK")
                .hasFieldOrPropertyWithValue("taskPriority", "LOW");
    }

    @Test
    void should_update_and_return_PerformerDto() {

        //Given
        UUID uuid = UUID.randomUUID();
        IncomingTaskDto given = new IncomingTaskDto();
        given.setTaskId(uuid);
        given.setTaskStatus("at WOrk");
        given.setTaskPriority("low");
        TaskEntity mockEntity = new TaskEntity();
        mockEntity.setTaskId(uuid);
        mockEntity.setTaskStatus("AT WORK");
        mockEntity.setTaskPriority("LOW");
        when(repository.save(any())).thenReturn(mockEntity);

        //When
        OutGoingTaskDto actual = service.saveOrUpdate(given);

        //Then
        assertThat(actual)
                .isNotNull()
                .hasFieldOrPropertyWithValue("taskId", uuid.toString())
                .hasFieldOrPropertyWithValue("taskStatus", "AT WORK")
                .hasFieldOrPropertyWithValue("taskPriority", "LOW");
    }

    @Test
    void deleteByIdCorrectTest() {

        //Given
        UUID uuid = UUID.randomUUID();

        //When
        String message = service.delete(uuid);

        //Then
        assertThat("Task with uuid: " + uuid + " was deleted").isEqualTo(message);
    }

    private Optional<TaskEntity> createEntity() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskId(UUID.randomUUID());
        taskEntity.setTaskName("Test Task");
        taskEntity.setTaskDescription("Test Task Description");
        taskEntity.setTaskPriority("High");
        taskEntity.setTaskStatus("Open");
        taskEntity.setTaskDeadline(new Timestamp(System.currentTimeMillis()));
        taskEntity.setTaskProject(new ProjectEntity(
                UUID.fromString("b4b35951-3713-45d6-bb51-6550bc29c731"),
                "project for teest",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 20 * 60)));
        taskEntity.setTaskPerformer(new PerformerEntity(
                UUID.fromString("b110160f-c3a8-4f04-82f8-393e98e5336e"),
                "Ivan",
                "ivan@gmail.com",
                "DEVELOPMENT"
        ));
        return Optional.of(taskEntity);
    }
}