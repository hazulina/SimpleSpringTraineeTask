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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.trainee.repository.ContainerConfig.postgres;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
class PerformerRepositoryTest {

    @Autowired
    private PerformerRepository performerRepository;

    public static PostgreSQLContainer<?> sharedPostgres = postgres;

    @BeforeAll
    static void init() {
        sharedPostgres.start();
        Awaitility.await().atMost(Durations.ONE_MINUTE).until(postgres::isRunning);
    }

    @Test
    void findAllPerformersWithTasks() {
        //Given

        //When
        Iterable<PerformerEntity> allWithTasks = performerRepository.findAllWithTasks();

        //Then
        assertThat(allWithTasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void findAllPerformersWithProjects() {
        //Given

        //When
        Iterable<PerformerEntity> allWithTasks = performerRepository.findAllWithProjects();

        //Then
        assertThat(allWithTasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void findByPerformerId() {
        //Given
        PerformerEntity given = new PerformerEntity();
        UUID performerId = UUID.randomUUID();
        given.setPerformerId(performerId);
        performerRepository.save(given);

        //When
        Optional<PerformerEntity> actual = performerRepository.findById(performerId);

        //Then
        assertThat(actual.orElseThrow()).isEqualTo(given);
    }

    @Test
    void findByPerformerIdWithTasks() {
        //Given
        PerformerEntity given = createEntity();
        UUID performerId = UUID.randomUUID();
        given.setPerformerId(performerId);
        performerRepository.save(given);

        UUID givenId = given.getPerformerTasks().get(0).getTaskId();

        //When
        PerformerEntity actual = performerRepository.findByIdWithTask(performerId).get();
        UUID actualId = actual.getPerformerTasks().get(0).getTaskId();

        //Then
        assertThat(actualId)
                .isEqualTo(givenId);
    }

    @Test
    void savePerformer() {
        //Given
        PerformerEntity given = createEntity();
        UUID givenId = UUID.randomUUID();
        given.setPerformerId(givenId);

        //When
        performerRepository.save(given);
        PerformerEntity actual = performerRepository.findById(givenId).orElseThrow();


        //Then
        assertThat(actual.getPerformerId())
                .isEqualTo(givenId);
    }

    @Test
    void deleteProject() {
        //Given
        PerformerEntity given = createEntity();
        UUID givenId = UUID.randomUUID();
        given.setPerformerId(givenId);

        //When
        performerRepository.save(given);
        performerRepository.delete(given);

        Optional<PerformerEntity> actual = performerRepository.findById(givenId);

        //Then
        assertThat(actual).isEmpty();
    }

    private PerformerEntity createEntity() {
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(new TaskEntity(
                UUID.fromString("52ab4cd4-fe26-4daa-b52b-f483c65aaca6"),
                "Sample Task",
                "This is a sample task.",
                "HIGH",
                "AT WORK",
                new Timestamp(System.currentTimeMillis()))
        );
        List<ProjectEntity> projectEntities = new ArrayList<>();
        projectEntities.add(new ProjectEntity(
                UUID.fromString("6c550c1c-f0c0-4063-a939-338cbc7dcf60"),
                "project for teest",
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis() + 20 * 60))
        );
        PerformerEntity entity = new PerformerEntity();
        entity.setEmail("ivan@gmail.com");
        entity.setPerformerTasks(taskEntities);
        entity.setPerformerProjects(projectEntities);
        return entity;
    }
}