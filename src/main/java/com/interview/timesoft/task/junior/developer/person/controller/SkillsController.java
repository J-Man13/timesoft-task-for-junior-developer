package com.interview.timesoft.task.junior.developer.person.controller;

import com.interview.timesoft.task.junior.developer.person.mapper.SkillsMapper;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;
import com.interview.timesoft.task.junior.developer.person.model.business.Skill;
import com.interview.timesoft.task.junior.developer.person.model.dto.*;
import com.interview.timesoft.task.junior.developer.person.service.PersonsService;
import com.interview.timesoft.task.junior.developer.person.service.SkillsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/skills")
public class SkillsController {


    private final SkillsService skillsService;
    private final PersonsService personsService;
    private final SkillsMapper skillsMapper;

    public SkillsController(final SkillsMapper skillsMapper,
                            final @Qualifier("skillsDbService") SkillsService skillsService,
                            final @Qualifier("personsDbService") PersonsService personsService) {
        this.skillsMapper = skillsMapper;
        this.skillsService = skillsService;
        this.personsService = personsService;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public SkillDto create(final @RequestBody SkillCreationRequestDto skillCreationRequestDto){
        Person person = personsService.getAllConjunct(
                PersonExtractionFilter.builder()
                        .id(skillCreationRequestDto.getPersonId())
                        .pagingFrom(0)
                        .pagingTo(1)
                        .ascending(PersonExtractionFilter.ConstantDefault.Field.ascending)
                        .orderByParameter(PersonExtractionFilter.OrderByParameter.id)
                        .build()
        ).get(0);

        Skill skill = Skill.builder()
                .name(skillCreationRequestDto.getSkillName())
                .person(person)
                .build();

        skill = skillsService.save(skill);
        SkillDto skillDto = skillsMapper.skillToSkillDto(skill);
        return skillDto;
    }

    @GetMapping(
            path="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public SkillDto read(@PathVariable Long id){
        Skill skill = skillsService.read(id);
        SkillDto skillDto = skillsMapper.skillToSkillDto(skill);
        return skillDto;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public List<SkillDto> readAll(){
        List<Skill> skillList = skillsService.readAll();
        List<SkillDto> skillDtoList = skillsMapper.skillListToSkillDtoList(skillList);
        return skillDtoList;
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public SkillDto update(final @RequestBody SkillUpdateRequestDto skillUpdateRequestDto){
        Skill skill = skillsMapper.skillUpdateRequestDtoToSkill(skillUpdateRequestDto);
        skill = skillsService.update(skill);
        return skillsMapper.skillToSkillDto(skill);
    }

    @DeleteMapping(
            path="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public SkillDto delete(@PathVariable Long id){
        Skill skill = skillsService.delete(id);
        return skillsMapper.skillToSkillDto(skill);
    }
}