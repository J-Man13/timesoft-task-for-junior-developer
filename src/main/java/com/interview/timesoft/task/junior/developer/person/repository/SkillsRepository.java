package com.interview.timesoft.task.junior.developer.person.repository;

import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends CrudRepository<SkillEntity, Long>
{
    void deleteById(Long id);

    List<SkillEntity> findAll();
}
