package org.trainee.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.dto.projection.TaskProjectionDto;
import org.trainee.entity.PerformerEntity;
import org.trainee.entity.ProjectEntity;
import org.trainee.entity.TaskEntity;
import org.trainee.mapper.PerformerDtoMapperImpl;
import org.trainee.repository.PerformerRepository;
import org.trainee.service.PerformerService;
import org.trainee.service.impl.PerformerServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


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
        when(repository.findByIdWithProject(any())).thenReturn(Optional.of(test));
        OutGoingPerformerDto given = dtoMapper.map(test);

        //when
        OutGoingPerformerDto actual = service.findById(testId);

        //then
        assertThat(actual)
                .isNotNull()
                .isExactlyInstanceOf(OutGoingPerformerDto.class)
                .isEqualTo(given);
    }

    @Test
    void should_verify_that_constructor_is_up() {
        //Given

        //When

        //Then
        assertThat(service).isNotNull().isInstanceOf(PerformerService.class);
    }

    @Test
    void should_findAll_and_return_List_of_PerformerDto() {

        //Given
        List<PerformerEntity> test = new ArrayList<>();
        test.add(createEntity(UUID.randomUUID()));
        when(repository.findAllWithProjects()).thenReturn(test);

        //When
        List<OutGoingPerformerDto> actual = service.findAll();


        //Then
        assertThat(actual).isNotNull();

    }

    @Test
    void should_save_and_return_PerformerDto() {
        //Given
        IncomingPerformerDto given = new IncomingPerformerDto();
        given.setRole("development");
        PerformerEntity mockEntity = new PerformerEntity();
        mockEntity.setRole("DEVELOPMENT");
        when(repository.save(any())).thenReturn(mockEntity);

        //When
        OutGoingPerformerDto actual = service.saveOrUpdate(given);
        //Then
        assertThat(actual).isNotNull().hasFieldOrPropertyWithValue("role", "DEVELOPMENT");
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
        when(repository.save(any())).thenReturn(mockEntity);

        //When
        OutGoingPerformerDto actual = service.saveOrUpdate(given);
        //Then
        assertThat(actual).isNotNull()
                .hasFieldOrPropertyWithValue("role", "DEVELOPMENT");
    }

    @Test
    void deleteByIdCorrectTest() {

        //Given
        UUID uuid = UUID.randomUUID();

        //When
        String message = service.delete(uuid);

        //Then
        assertThat("Performer with uuid: " + uuid + " was deleted").isEqualTo(message);
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