package com.interview.timesoft.task.junior.developer.position.mapper;

import com.interview.timesoft.task.junior.developer.person.mapper.PersonsMapper;

import com.interview.timesoft.task.junior.developer.position.model.business.Worker;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkerDto;
import com.interview.timesoft.task.junior.developer.position.model.entity.WorkerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, uses = PersonsMapper.class)
public interface WorkersMapper{
    @Mapping(target="personEntity", source="worker.person")
    WorkerEntity workerToWorkerEntity(Worker worker);
    @Mapping(target="person", source="workerEntity.personEntity")
    Worker workerEntityToWorker(WorkerEntity workerEntity);
    WorkerDto workerToWorkerDto(Worker worker);
    List<WorkerDto> workerListToWorkerDtoList(List<Worker> workerList);
    List<Worker> workerEntityListToWorkerList(List<WorkerEntity> workerEntityList);

    @Mapping(target="personEntity", source="worker.person")
    WorkerEntity updateWorkerEntityByWorker(@MappingTarget WorkerEntity workerEntityToUpdate, Worker worker);
}
