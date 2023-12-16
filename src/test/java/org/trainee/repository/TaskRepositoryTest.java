package org.trainee.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.org.awaitility.Awaitility;
import org.testcontainers.shaded.org.awaitility.Durations;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.trainee.repository.ContainerConfig.postgres;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    public static PostgreSQLContainer<?> sharedPostgres = postgres;

    @BeforeAll
    static void init() {
        sharedPostgres.start();
        Awaitility.await().atMost(Durations.ONE_MINUTE).until(postgres::isRunning);
    }

    @Test
    void findByTaskId() {
        //Given
        Optional<TaskEntity> given = createEntity();
        taskRepository.save(given.get());

        //When
        Optional<TaskEntity> actual = taskRepository.findByTaskId(given.orElseThrow().getTaskId());

        //Then
        assertThat(actual).isEqualTo(given);
    }

    @Test
    void findAllTasks() {
        //Given

        //When
        Iterable<TaskEntity> allTasks = taskRepository.findAllTasks();

        //Then
        assertThat(allTasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void saveTask() {
        //Given
        TaskEntity given = new TaskEntity();
        UUID taskId = UUID.randomUUID();
        given.setTaskId(taskId);

        //When
        taskRepository.save(given);
        Optional<TaskEntity> actual = taskRepository.findByTaskId(taskId);

        //Then
        assertThat(actual.orElseThrow()).isEqualTo(given);
    }

    @Test
    void deleteTask() {
        //Given
        TaskEntity given = new TaskEntity();
        UUID taskId = UUID.randomUUID();
        given.setTaskId(taskId);

        //When
        taskRepository.save(given);
        taskRepository.delete(given);
        Optional<TaskEntity> actual = taskRepository.findByTaskId(taskId);

        //Then
        assertThat(actual).isEmpty();
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
                UUID.fromString("bf10a3c9-4c95-4239-8404-48e96d90ffac"),
                "Ivan",
                "ivan@gmail.com",
                "DEVELOPMENT"
        ));
        return Optional.of(taskEntity);
    }
}