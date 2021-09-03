package com.interview.timesoft.task.junior.developer.person.service;

import com.interview.timesoft.task.junior.developer.person.model.business.Skill;

import java.util.List;

public interface SkillsService {
    Skill save(Skill skill);
    Skill read(Long id);
    List<Skill> readAll();
    Skill delete(Long id);
    Skill update(Skill skill);
}
