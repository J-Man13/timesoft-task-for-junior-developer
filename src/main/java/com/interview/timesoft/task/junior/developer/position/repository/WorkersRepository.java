package com.interview.timesoft.task.junior.developer.position.repository;

import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import com.interview.timesoft.task.junior.developer.position.model.entity.WorkerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkersRepository extends CrudRepository<WorkerEntity, Long> {
    List<WorkerEntity> findAll();
}
