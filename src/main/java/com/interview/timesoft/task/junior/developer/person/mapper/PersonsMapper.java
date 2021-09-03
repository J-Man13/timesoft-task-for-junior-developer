package com.interview.timesoft.task.junior.developer.person.mapper;

import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;
import com.interview.timesoft.task.junior.developer.person.model.business.Skill;
import com.interview.timesoft.task.junior.developer.person.model.dto.*;
import com.interview.timesoft.task.junior.developer.person.model.entity.PersonEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PersonsMapper {

    Person personCreationRequestDtoToPerson(PersonCreationRequestDto personCreationRequestDto);

    Person personUpdateRequestDtoToPerson(PersonUpdateRequestDto personUpdateRequestDto);

    @Mapping(target="skillList", source="personDto.personSkillDtoList")
    Person personDtoToPerson(PersonDto personDto);

    @Mapping(target="personSkillDtoList", source="person.skillList")
    PersonDto personToPersonDto(Person person);

    PersonSkillDto  skillToPersonSkillDto(Skill skill);

    List<PersonDto> personListToPersonDtoList(List<Person> personList);

    @Mapping(target="skillEntityList", source="person.skillList")
    PersonEntity personToPersonEntity(Person person);

    PersonEntity updatePersonEntityByPerson(@MappingTarget PersonEntity personEntityToUpdate, Person person);


    @Mapping(target="skillList", source="personEntity.skillEntityList")
    Person personEntityToPerson(PersonEntity personEntity);

    List<Person> personEntityListToPersonList(List<PersonEntity> personEntityList);

    @Mapping(target = "pagingFrom", defaultValue = PersonExtractionFilter.ConstantDefault.Field.pagingFromString)
    @Mapping(target = "pagingTo", defaultValue = PersonExtractionFilter.ConstantDefault.Field.pagingToString)
    @Mapping(target = "ascending", defaultValue = PersonExtractionFilter.ConstantDefault.Field.ascendingString)
    @Mapping(target = "orderByParameter", defaultValue = PersonExtractionFilter.ConstantDefault.Field.orderByParameterString)
    PersonExtractionFilter personExtractionRequestDtoToPersonExtractionFilter(PersonExtractionRequestDto personExtractionRequestDto);
}
