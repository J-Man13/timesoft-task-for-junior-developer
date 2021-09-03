package com.interview.timesoft.task.junior.developer.person.repository;


import com.interview.timesoft.task.junior.developer.person.model.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonsRepository extends
        CrudRepository<PersonEntity, Long>,
        JpaSpecificationExecutor<PersonEntity>,
        PagingAndSortingRepository<PersonEntity, Long>
{
    void deleteById(Long id);
}
