package com.interview.timesoft.task.junior.developer.person.service;

import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.mapper.PersonsMapper;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;
import com.interview.timesoft.task.junior.developer.person.model.entity.PersonEntity;
import com.interview.timesoft.task.junior.developer.person.repository.PersonsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonsDbService implements PersonsService {
    private final PersonsMapper personsMapper;
    private final PersonsRepository personsRepository;

    public PersonsDbService(final PersonsMapper personsMapper,
                            final PersonsRepository personsRepository){
        this.personsMapper = personsMapper;
        this.personsRepository = personsRepository;

    }

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = personsMapper.personToPersonEntity(person);
        personEntity.setEmploymentStatus(EmploymentStatus.UNEMPLOYED.name());
        personEntity.setCreated(LocalDateTime.now());
        personEntity = personsRepository.save(personEntity);
        return personsMapper.personEntityToPerson(personEntity);
    }

    @Override
    public Person update(Person person)
    {
        PersonEntity personEntityToUpdate = personsRepository.findById(person.getId()).orElseThrow(()->new NullPointerException("No person to update"));
        personEntityToUpdate =  personsMapper.updatePersonEntityByPerson(personEntityToUpdate,person);
        personEntityToUpdate.setUpdated(LocalDateTime.now());
        personEntityToUpdate = personsRepository.save(personEntityToUpdate);
        return personsMapper.personEntityToPerson(personEntityToUpdate);
    }

    @Override
    public List<Person> getAllConjunct(PersonExtractionFilter personExtractionFilter)
    {
        Specification<PersonEntity> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicateList = new LinkedList<>();

            if (Objects.nonNull(personExtractionFilter.getId()))
                predicateList.add(
                        criteriaBuilder.equal(
                                root.<Long>get(PersonEntity.ColumnName.Constant.id),
                                personExtractionFilter.getId())
                );

            if (!StringUtils.isEmpty(personExtractionFilter.getName()))
                predicateList.add(
                        criteriaBuilder.equal(
                                root.<String>get(PersonEntity.ColumnName.Constant.name),
                                personExtractionFilter.getName()
                        )
                );


            if (!StringUtils.isEmpty(personExtractionFilter.getSurname()))
                predicateList.add(
                        criteriaBuilder.equal(
                                root.<String>get(PersonEntity.ColumnName.Constant.surname),
                                personExtractionFilter.getSurname())
                );

            if (Objects.nonNull(personExtractionFilter.getSex()))
                predicateList.add(criteriaBuilder.equal(
                        root.<String>get(PersonEntity.ColumnName.Constant.sex),
                        personExtractionFilter.getSex().name())
                );

            if (Objects.nonNull(personExtractionFilter.getAge()))
                predicateList.add(
                        criteriaBuilder.equal(
                                root.<Long>get(PersonEntity.ColumnName.Constant.age),
                                personExtractionFilter.getAge()
                        )
                );

            if (Objects.nonNull(personExtractionFilter.getEmploymentStatus()))
                predicateList.add(criteriaBuilder.equal(
                        root.<String>get(PersonEntity.ColumnName.Constant.employmentStatus),
                        personExtractionFilter.getEmploymentStatus().name()
                        )
                );

            if (personExtractionFilter.getAscending())
                query.orderBy(criteriaBuilder.asc(root.get(personExtractionFilter.getOrderByParameter().name())));
            else
                query.orderBy(criteriaBuilder.desc(root.get(personExtractionFilter.getOrderByParameter().name())));

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };


        PageRequest pageRequest = PageRequest.of(personExtractionFilter.getPagingFrom(), personExtractionFilter.getPagingTo());

        Page<PersonEntity> personEntityPage = personsRepository.findAll(specification, pageRequest);

        List<PersonEntity> personEntityList = personEntityPage.get().collect(Collectors.toList());

        return personsMapper.personEntityListToPersonList(personEntityList);
    }

    @Override
    public Person delete(Long personId) {
        PersonEntity personEntityToDelete = personsRepository.findById(personId).orElseThrow(()->new NullPointerException("No person to delete"));
        personsRepository.deleteById(personId);
        return personsMapper.personEntityToPerson(personEntityToDelete);
    }
}
