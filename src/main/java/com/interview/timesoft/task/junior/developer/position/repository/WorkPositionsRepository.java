package com.interview.timesoft.task.junior.developer.position.repository;

import com.interview.timesoft.task.junior.developer.position.model.entity.WorkPositionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPositionsRepository extends CrudRepository<WorkPositionEntity, Long> {
    List<WorkPositionEntity> findAll();
}
