package com.interview.timesoft.task.junior.developer.person.service;


import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.mapper.SkillsMapper;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.Skill;
import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import com.interview.timesoft.task.junior.developer.person.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SkillsDbService implements SkillsService {

    private final SkillsMapper skillsMapper;
    private final SkillsRepository skillsRepository;
    private final PersonsService personsService;

    public SkillsDbService(final SkillsRepository skillsRepository,
                           final @Qualifier("personsDbService") PersonsService personsService,
                           final SkillsMapper skillsMapper) {
        this.skillsRepository = skillsRepository;
        this.personsService = personsService;
        this.skillsMapper = skillsMapper;
    }

    @Transactional
    @Override
    public Skill save(Skill skill) {
        Person person = skill.getPerson();
        person.setEmploymentStatus(EmploymentStatus.JOB_SEEKER);
        person = personsService.update(person);
        skill.setPerson(person);
        SkillEntity skillEntity = skillsMapper.skillToSkillEntity(skill);
        skillEntity.setCreated(LocalDateTime.now());
        skillEntity = skillsRepository.save(skillEntity);
        skill = skillsMapper.skillEntityToSkill(skillEntity);
        return skill;
    }

    @Override
    public Skill read(Long id) {
        SkillEntity skillEntity = skillsRepository.findById(id).orElse(null);
        Skill skill = skillsMapper.skillEntityToSkill(skillEntity);
        return skill;
    }

    @Override
    public List<Skill> readAll() {
        List<SkillEntity> skillEntityList = skillsRepository.findAll();
        return skillsMapper.skillEntityListToSkillList(skillEntityList);
    }

    @Override
    public Skill delete(Long id) {
        SkillEntity skillEntity = skillsRepository.findById(id).orElseThrow(()->new NullPointerException("No skill to delete"));
        skillsRepository.delete(skillEntity);
        Skill skill = skillsMapper.skillEntityToSkill(skillEntity);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        SkillEntity skillEntityToUpdate = skillsRepository.findById(skill.getId()).orElseThrow(()->new NullPointerException("No skill to update"));
        skillEntityToUpdate = skillsMapper.updateSkillEntityBySkill(skillEntityToUpdate,skill);
        skillEntityToUpdate.setUpdated(LocalDateTime.now());
        skillEntityToUpdate = skillsRepository.save(skillEntityToUpdate);
        return skillsMapper.skillEntityToSkill(skillEntityToUpdate);
    }
}
