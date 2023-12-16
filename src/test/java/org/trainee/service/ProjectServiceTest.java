package org.trainee.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;
import org.trainee.mapper.ProjectDtoMapperImpl;
import org.trainee.repository.ProjectRepository;
import org.trainee.service.impl.ProjectServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


class ProjectServiceTest {
    ProjectRepository repository = Mockito.mock(ProjectRepository.class);
    ProjectDtoMapperImpl dtoMapper = Mockito.spy(ProjectDtoMapperImpl.class);
    @InjectMocks
    ProjectService service = new ProjectServiceImpl(repository, dtoMapper);

    @Test
    void should_find_By_Id_and_return_ProjectDto_Correct_Test() {

        //Given
        UUID testId = UUID.randomUUID();
        Optional<ProjectEntity> test = Optional.of(createEntity());
        Mockito.when(repository.findByIdWithPerformers(ArgumentMatchers.any())).thenReturn(test);
        OutGoingProjectDto given = dtoMapper.map(test.orElseThrow());
        //When
        OutGoingProjectDto result = service.findById(testId);

        //Then
        Assertions.assertThat(result)
                .isNotNull()
                .isExactlyInstanceOf(OutGoingProjectDto.class)
                .isEqualTo(given);
    }


    @Test
    void should_findAll_and_return_List_of_ProjectDto() {

        //Given
        Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());

        //When
        List<OutGoingProjectDto> actual = service.findAll();

        //Then
        Assertions.assertThat(actual).isNotNull();
    }

    @Test
    void should_save_and_return_ProjectDto() {

        //Given
        IncomingProjectDto given = new IncomingProjectDto();
        given.setProjectName("best project");

        ProjectEntity mockEntity = new ProjectEntity();
        mockEntity.setProjectName("best project");

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(mockEntity);

        //When
        OutGoingProjectDto actual = service.saveOrUpdate(given);

        //Then
        Assertions.assertThat(actual).isNotNull().hasFieldOrPropertyWithValue("projectName", "best project");
    }

    @Test
    void should_update_and_return_ProjectDto() {

        //Given
        UUID uuid = UUID.randomUUID();
        IncomingProjectDto given = new IncomingProjectDto();
        given.setProjectId(uuid);
        given.setProjectName("best project");

        ProjectEntity mockEntity = new ProjectEntity();
        mockEntity.setProjectId(uuid);
        mockEntity.setProjectName("best project");

        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(mockEntity);

        //When
        OutGoingProjectDto actual = service.saveOrUpdate(given);

        //Then
        Assertions.assertThat(actual).isNotNull()
                .hasFieldOrPropertyWithValue("projectName", "best project")
                .hasFieldOrPropertyWithValue("projectId", uuid.toString());
    }

    @Test
    void should_delete_and_return_true() {

        //Given
        UUID uuid = UUID.randomUUID();

        //When
        String message = service.delete(uuid);

        //Then
        Assertions.assertThat("Project with uuid: " + uuid + " was deleted").isEqualTo(message);
    }

    private ProjectEntity createEntity() {
        List<PerformerEntity> performerEntities = new ArrayList<>();
        performerEntities.add(new PerformerEntity(
                        UUID.fromString("b110160f-c3a8-4f04-82f8-393e98e5336e"),
                        "Ivan",
                        "ivan@gmail.com",
                        "DEVELOPMENT"
                )
        );
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(new TaskEntity(
                UUID.fromString("cd90a995-78cb-4acc-a8a2-27df75e91768"),
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