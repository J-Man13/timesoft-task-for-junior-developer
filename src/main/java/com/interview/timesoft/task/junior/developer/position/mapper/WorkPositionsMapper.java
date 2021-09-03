package com.interview.timesoft.task.junior.developer.position.mapper;

import com.interview.timesoft.task.junior.developer.position.model.business.WorkPosition;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkPositionDto;
import com.interview.timesoft.task.junior.developer.position.model.entity.WorkPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = WorkersMapper.class)
public interface WorkPositionsMapper {
    @Mapping(target="workerEntity", source="workPosition.worker")
    WorkPositionEntity workPositionToWorkPositionEntity(WorkPosition workPosition);
    List<WorkPosition> workPositionEntityListToWorkPositionList(List<WorkPositionEntity> workPositionEntityList);
    WorkPositionDto workPositionToWorkPositionDto(WorkPosition workPosition);

    List<WorkPositionDto> workPositionListToWorkPositionDtoList(List<WorkPosition> workPositionList);
    @Mapping(target="worker", source="workPositionEntity.workerEntity")
    WorkPosition workPositionEntityToWorkPosition(WorkPositionEntity workPositionEntity);


    WorkPosition workPositionDtoToWorkPosition(WorkPositionDto workPositionDto);


    WorkPosition updateWorkPositionEntityByWorkPosition(@MappingTarget WorkPosition workPositionEntityToUpdate,  WorkPosition workPosition);
}
