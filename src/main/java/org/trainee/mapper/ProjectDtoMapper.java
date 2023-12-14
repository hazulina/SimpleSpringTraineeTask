package org.trainee.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.trainee.dto.IncomingProjectDto;
import org.trainee.dto.OutGoingProjectDto;
import org.trainee.entity.ProjectEntity;

/**
 * Converts entity to DTO and back with <a href="https://mapstruct.org/">MapStruct</a>
 * Implementation - {@link ProjectDtoMapperImpl}
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProjectDtoMapper {
    ProjectEntity map(IncomingProjectDto incomingProjectDto);

    OutGoingProjectDto map(ProjectEntity projectEntity);
}
