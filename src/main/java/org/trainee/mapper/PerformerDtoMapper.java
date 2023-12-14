package org.trainee.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.trainee.dto.IncomingPerformerDto;
import org.trainee.dto.OutGoingPerformerDto;
import org.trainee.dto.projection.PerformerProjectionDto;
import org.trainee.entity.PerformerEntity;

/**
 * Converts entity to DTO and back with <a href="https://mapstruct.org/">MapStruct</a>
 * Implementation - {@link PerformerDtoMapperImpl}
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PerformerDtoMapper {
    PerformerEntity map(IncomingPerformerDto incomingPerformerDto);

    OutGoingPerformerDto map(PerformerEntity performerEntity);
    PerformerProjectionDto mapProjection(PerformerEntity performerEntity);
}
