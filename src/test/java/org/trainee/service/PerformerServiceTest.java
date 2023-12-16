package org.trainee.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;
import org.trainee.mapper.PerformerDtoMapperImpl;
import org.trainee.repository.PerformerRepository;
import org.trainee.service.impl.PerformerServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


class PerformerServiceTest {


    private final PerformerRepository repository = Mockito.mock(PerformerRepository.class);

    private final PerformerDtoMapperImpl dtoMapper = Mockito.spy(PerformerDtoMapperImpl.class);

    @InjectMocks
    private PerformerService service = new PerformerServiceImpl(repository, dtoMapper);


    @Test
    void should_find_By_Id_and_return_Performer_dto_CorrectTest() {
        //given
        UUID testId = UUID.randomUUID();
        PerformerEntity test = createEntity(testId);
        Mockito.when(repository.findByIdWithProject(ArgumentMatchers.any())).thenReturn(Optional.of(test));
        OutGoingPerformerDto given = dtoMapper.map(test);

        //when
        OutGoingPerformerDto actual = service.findById(testId);

        //then
        Assertions.assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(OutGoingPerformerDto.class)
                .isEqualTo(given);
    }

    @Test
    void should_verify_that_constructor_is_up() {
        //Given

        //When

        //Then
        Assertions.assertThat(service).isNotNull().isInstanceOf(PerformerService.class);
    }

    @Test
    void should_findAll_and_return_List_of_PerformerDto() {

        //Given
        List<PerformerEntity> test = new ArrayList<>();
        test.add(createEntity(UUID.randomUUID()));
        Mockito.when(repository.findAllWithProjects()).thenReturn(test);

        //When
        List<OutGoingPerformerDto> actual = service.findAll();


        //Then
        Assertions.assertThat(actual).isNotNull();

    }

    @Test
    void should_save_and_return_PerformerDto() {
        //Given
        IncomingPerformerDto given = new IncomingPerformerDto();
        given.setRole("development");
        PerformerEntity mockEntity = new PerformerEntity();
        mockEntity.setRole("DEVELOPMENT");
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(mockEntity);

        //When
        OutGoingPerformerDto actual = service.saveOrUpdate(given);
        //Then
        Assertions.assertThat(actual).isNotNull().hasFieldOrPropertyWithValue("role", "DEVELOPMENT");
    }

    @Test
    void should_update_and_return_PerformerDto() {
        //Given
        UUID uuid = UUID.randomUUID();
        IncomingPerformerDto given = new IncomingPerformerDto();
        given.setPerformerId(uuid);
        given.setRole("development");
        PerformerEntity mockEntity = new PerformerEntity();
        mockEntity.setRole("DEVELOPMENT");
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(mockEntity);

        //When
        OutGoingPerformerDto actual = service.saveOrUpdate(given);
        //Then
        Assertions.assertThat(actual).isNotNull()
                .hasFieldOrPropertyWithValue("role", "DEVELOPMENT");
    }

    @Test
    void deleteByIdCorrectTest() {

        //Given
        UUID uuid = UUID.randomUUID();

        //When
        String message = service.delete(uuid);

        //Then
        Assertions.assertThat("Performer with uuid: " + uuid + " was deleted").isEqualTo(message);
    }

    private PerformerEntity createEntity(UUID testId) {
        List<TaskEntity> taskEntities = new ArrayList<>();
        taskEntities.add(new TaskEntity(
                UUID.fromString("cd90a995-78cb-4acc-a8a2-27df75e91768"),
                "Sample Task",
                "This is a sample task.",
                "HIGH",
                "AT WORK",
                new Timestamp(System.currentTimeMillis()))
        );
        List<ProjectEntity> projectEntities = new ArrayList<>();
        projectEntities.add(new ProjectEntity(
                        UUID.fromString("b4b35951-3713-45d6-bb51-6550bc29c731"),
                        "project for teest",
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis() + 20 * 60))
        );
        PerformerEntity entity = new PerformerEntity();
        entity.setPerformerId(testId);
        entity.setEmail("ivan@gmail.com");
        entity.setPerformerTasks(taskEntities);
        entity.setPerformerProjects(projectEntities);
        return entity;
    }
}