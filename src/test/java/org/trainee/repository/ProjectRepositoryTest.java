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
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    public static PostgreSQLContainer<?> sharedPostgres = postgres;

    @BeforeAll
    static void init() {
        sharedPostgres.start();
        Awaitility.await().atMost(Durations.ONE_MINUTE).until(postgres::isRunning);
    }


    @Test
    void findAllProjectsWithTasks() {
        //Given

        //When
        Iterable<ProjectEntity> allWithTasks = projectRepository.findAllWithTasks();

        //Then
        assertThat(allWithTasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void findAllProjectsWithPerformers() {
        //Given

        //When
        Iterable<ProjectEntity> allWithTasks = projectRepository.findAllWithPerformers();

        //Then
        assertThat(allWithTasks)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void findByProjectId() {
        //Given
        ProjectEntity given = new ProjectEntity();
        UUID projectId = UUID.randomUUID();
        given.setProjectId(projectId);
        projectRepository.save(given);

        //When
        Optional<ProjectEntity> actual = projectRepository.findById(projectId);

        //Then
        assertThat(actual.orElseThrow()).isEqualTo(given);
    }

    @Test
    void findByProjectIdWithTasks() {
        //Given
        ProjectEntity given = createProjectEntity();
        UUID projectId = UUID.randomUUID();
        given.setProjectId(projectId);
        projectRepository.save(given);

        UUID givenId = given.getProjectTasks().get(0).getTaskId();

        //When
        ProjectEntity actual = projectRepository.findByIdWithTasks(projectId).get();
        UUID actualId = actual.getProjectTasks().get(0).getTaskId();

        //Then
        assertThat(actualId)
                .isEqualTo(givenId);
    }

    @Test
    void saveProject() {
        //Given
        ProjectEntity given = createProjectEntity();
        UUID givenId = UUID.randomUUID();
        given.setProjectId(givenId);


        //When
        projectRepository.save(given);
        ProjectEntity actual = projectRepository.findByIdWithTasks(givenId).get();


        //Then
        assertThat(actual.getProjectId())
                .isEqualTo(givenId);
    }

    @Test
    void deleteProject() {
        //Given
        ProjectEntity given = createProjectEntity();
        UUID givenId = UUID.randomUUID();
        given.setProjectId(givenId);

        //When
        projectRepository.save(given);
        projectRepository.delete(given);
        Optional<ProjectEntity> actual = projectRepository.findByIdWithTasks(givenId);

        //Then
        assertThat(actual).isEmpty();
    }

    private ProjectEntity createProjectEntity() {
        List<PerformerEntity> performerEntities = new ArrayList<>();
        performerEntities.add(new PerformerEntity(
                        UUID.randomUUID(),
                        "Ivan",
                        "ivan@gmail.com",
                        "DEVELOPMENT"
                )
        );
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(new TaskEntity(
                UUID.randomUUID(),
                "Sample Task",
                "This is a sample task.",
                "HIGH",
                "AT WORK",
                new Timestamp(System.currentTimeMillis()))
        );
        ProjectEntity entity = new ProjectEntity();
        entity.setProjectStartDate(Timestamp.valueOf("2023-01-20 12:34:56"));
        entity.setProjectDeadlineDate(Timestamp.valueOf("2023-11-20 12:34:56"));
        entity.setProjectTasks(taskEntities);
        entity.setProjectPerformers(performerEntities);
        return entity;
    }
}