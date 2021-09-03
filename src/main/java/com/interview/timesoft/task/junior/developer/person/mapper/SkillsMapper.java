package com.interview.timesoft.task.junior.developer.person.mapper;


import com.interview.timesoft.task.junior.developer.person.model.business.Skill;
import com.interview.timesoft.task.junior.developer.person.model.dto.SkillDto;
import com.interview.timesoft.task.junior.developer.person.model.dto.SkillUpdateRequestDto;
import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,uses = PersonsMapper.class)
public interface SkillsMapper {

    @Mappings(
            {
                    @Mapping(target="personDto", source="skill.person"),
                    @Mapping(target="personDto.personSkillDtoList", ignore = true),
            }
    )
    SkillDto skillToSkillDto(Skill skill);


    List<SkillDto> skillListToSkillDtoList(List<Skill> skillList);

    @Mappings(
            {
                    @Mapping(target="person", source="skillDto.personDto"),
                    @Mapping(target="person.skillList", ignore = true),
            }
    )
    Skill skillDtoToSkill(SkillDto skillDto);


    @Mappings(
            {
                    @Mapping(target="personEntity", source="skill.person"),
                    @Mapping(target="personEntity.skillEntityList", ignore = true),
            }
    )
    SkillEntity skillToSkillEntity(Skill skill);

    @Mappings(
            {
                    @Mapping(target="person", source="skillEntity.personEntity"),
                    @Mapping(target="person.skillList", ignore = true),
            }
    )
    Skill skillEntityToSkill(SkillEntity skillEntity);

    List<Skill> skillEntityListToSkillList(List<SkillEntity> skillEntityList);

    @Mappings(
            {
                    @Mapping(target="personEntity.skillEntityList", ignore = true),
            }
    )
    SkillEntity updateSkillEntityBySkill(@MappingTarget SkillEntity skillEntity, Skill skill);


    Skill skillUpdateRequestDtoToSkill(SkillUpdateRequestDto skillUpdateRequestDto);
}
