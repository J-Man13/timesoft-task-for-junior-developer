package com.interview.timesoft.task.junior.developer.person.controller;


import com.interview.timesoft.task.junior.developer.person.mapper.PersonsMapper;
import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;
import com.interview.timesoft.task.junior.developer.person.model.dto.PersonCreationRequestDto;
import com.interview.timesoft.task.junior.developer.person.model.dto.PersonDto;
import com.interview.timesoft.task.junior.developer.person.model.dto.PersonExtractionRequestDto;
import com.interview.timesoft.task.junior.developer.person.model.dto.PersonUpdateRequestDto;
import com.interview.timesoft.task.junior.developer.person.service.PersonsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    private final PersonsMapper personsMapper;
    private final PersonsService personsService;

    public PersonsController(final PersonsMapper personsMapper,
                             final @Qualifier("personsDbService") PersonsService personsService) {
        this.personsMapper = personsMapper;
        this.personsService = personsService;
    }


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public PersonDto create(final @RequestBody PersonCreationRequestDto personCreationRequestDto){
        Person person = personsMapper.personCreationRequestDtoToPerson(personCreationRequestDto);
        person = personsService.save(person);
        PersonDto personDto = personsMapper.personToPersonDto(person);
        return personDto;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public List<PersonDto> read(PersonExtractionRequestDto personExtractionRequestDto){
        PersonExtractionFilter personExtractionFilter = personsMapper.personExtractionRequestDtoToPersonExtractionFilter(personExtractionRequestDto);
        List<Person> personList = personsService.getAllConjunct(personExtractionFilter);
        List<PersonDto> personDtoList = personsMapper.personListToPersonDtoList(personList);
        return personDtoList;
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public PersonDto update(final @RequestBody PersonUpdateRequestDto personUpdateRequestDto){
        Person person = personsMapper.personUpdateRequestDtoToPerson(personUpdateRequestDto);
        person.setUpdated(LocalDateTime.now());
        person = personsService.update(person);
        PersonDto personDto = personsMapper.personToPersonDto(person);
        return personDto;
    }

    @DeleteMapping(
            path="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public PersonDto delete(@PathVariable Long id){
        Person person = personsService.delete(id);
        PersonDto personDto = personsMapper.personToPersonDto(person);
        return personDto;
    }
}
