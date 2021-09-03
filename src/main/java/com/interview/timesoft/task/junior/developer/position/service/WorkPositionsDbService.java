package com.interview.timesoft.task.junior.developer.position.service;

import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.service.PersonsService;
import com.interview.timesoft.task.junior.developer.position.mapper.WorkPositionsMapper;
import com.interview.timesoft.task.junior.developer.position.model.business.WorkPosition;
import com.interview.timesoft.task.junior.developer.position.model.business.WorkPositionStatus;
import com.interview.timesoft.task.junior.developer.position.model.entity.WorkPositionEntity;
import com.interview.timesoft.task.junior.developer.position.repository.WorkPositionsRepository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class WorkPositionsDbService implements WorkPositionsService {

    private final WorkPositionsMapper workPositionsMapper;
    private final WorkPositionsRepository workPositionsRepository;
    private final PersonsService personsService;

    public WorkPositionsDbService(final WorkPositionsMapper workPositionsMapper,
                                  final WorkPositionsRepository workPositionsRepository,
                                  final @Qualifier("workersDbService") WorkersService workersService,
                                  final PersonsService personsService) {
        this.workPositionsMapper = workPositionsMapper;
        this.workPositionsRepository = workPositionsRepository;
        this.personsService = personsService;
    }


    @Override
    public WorkPosition save(WorkPosition workPosition) {
        WorkPositionEntity workPositionEntity = workPositionsMapper.workPositionToWorkPositionEntity(workPosition);
        workPositionEntity.setCreated(LocalDateTime.now());
        workPositionEntity.setWorkPositionStatus(WorkPositionStatus.INACTIVE.name());
        workPositionEntity = workPositionsRepository.save(workPositionEntity);
        return workPositionsMapper.workPositionEntityToWorkPosition(workPositionEntity);
    }

    @Override
    public List<WorkPosition> readAll() {
        List<WorkPositionEntity> workPositionEntityList = workPositionsRepository.findAll();
        return workPositionsMapper.workPositionEntityListToWorkPositionList(workPositionEntityList);
    }

    @Override
    public WorkPosition read(Long id) {
        WorkPositionEntity workPositionEntity = workPositionsRepository.findById(id).orElseThrow(()->new NullPointerException("Could not find work position by id"));
        return workPositionsMapper.workPositionEntityToWorkPosition(workPositionEntity);
    }

    @Override
    public WorkPosition delete(Long id) {
        WorkPositionEntity workPositionEntity = workPositionsRepository.findById(id).orElseThrow(()->new NullPointerException("Could not find work position by id"));
        workPositionsRepository.delete(workPositionEntity);
        return workPositionsMapper.workPositionEntityToWorkPosition(workPositionEntity);
    }

    @Transactional
    @Override
    public WorkPosition update(WorkPosition workPosition) {
        WorkPosition workPositionToUpdate = read(workPosition.getId());
        workPositionToUpdate = workPositionsMapper.updateWorkPositionEntityByWorkPosition(workPositionToUpdate,workPosition);

        if (
           workPositionToUpdate.getWorkPositionStatus() == WorkPositionStatus.VACANT &&
           Objects.nonNull(workPositionToUpdate.getWorker()) &&
           Objects.nonNull(workPositionToUpdate.getWorker().getPerson())
        )
        {
            Person person = workPosition.getWorker().getPerson();
            person.setEmploymentStatus(EmploymentStatus.EMPLOYED);
            person = personsService.update(person);
            workPosition.getWorker().setPerson(person);

            workPositionToUpdate.setWorkPositionStatus(WorkPositionStatus.CAUGHT);
        }

        WorkPositionEntity workPositionEntityToUpdate = workPositionsMapper.workPositionToWorkPositionEntity(workPositionToUpdate);
        workPositionEntityToUpdate.setUpdated(LocalDateTime.now());
        workPositionEntityToUpdate = workPositionsRepository.save(workPositionEntityToUpdate);

        return workPositionsMapper.workPositionEntityToWorkPosition(workPositionEntityToUpdate);
    }
}
