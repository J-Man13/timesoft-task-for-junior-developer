package com.interview.timesoft.task.junior.developer.person.service;

import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;

import java.util.List;

public interface PersonsService {
    Person save(Person person);
    Person update(Person person);
    List<Person> getAllConjunct(PersonExtractionFilter personExtractionFilter);
    Person delete(Long personId);
}
