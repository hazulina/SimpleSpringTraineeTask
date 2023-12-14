package org.trainee.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.trainee.dto.IncomingTaskDto;
import org.trainee.dto.OutGoingTaskDto;
import org.trainee.entity.TaskEntity;

import java.sql.Timestamp;

/**
 * Converts entity to DTO and back with <a href="https://mapstruct.org/">MapStruct</a>
 * Implementation - {@link TaskDtoMapperImpl}
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TaskDtoMapper {
    TaskEntity map(IncomingTaskDto incomingDto);
    OutGoingTaskDto map(TaskEntity taskEntity);

    default Timestamp map(String value) {
        return Timestamp.valueOf(value);
    }
}
