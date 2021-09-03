package com.interview.timesoft.task.junior.developer.position.controller;


import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import com.interview.timesoft.task.junior.developer.person.model.business.PersonExtractionFilter;
import com.interview.timesoft.task.junior.developer.person.service.PersonsService;
import com.interview.timesoft.task.junior.developer.position.mapper.WorkersMapper;
import com.interview.timesoft.task.junior.developer.position.model.business.Worker;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkerCreationRequestDto;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkerDto;
import com.interview.timesoft.task.junior.developer.position.service.WorkersService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkersController {

    private final PersonsService personsService;
    private final WorkersService workersService;
    private final WorkersMapper workersMapper;

    public WorkersController(final @Qualifier("personsDbService") PersonsService personsService,
                             final @Qualifier("workersDbService") WorkersService workersService,
                             final WorkersMapper workersMapper) {
        this.personsService = personsService;
        this.workersService = workersService;
        this.workersMapper = workersMapper;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public WorkerDto create(final @RequestBody WorkerCreationRequestDto workerCreationRequestDto){
        Person person = personsService.getAllConjunct(
                PersonExtractionFilter.builder()
                        .id(workerCreationRequestDto.getPersonId())
                        .pagingFrom(0)
                        .pagingTo(1)
                        .ascending(true)
                        .orderByParameter(PersonExtractionFilter.OrderByParameter.id)
                        .build()).get(0);
        Worker worker = Worker.builder().person(person).build();
        worker = workersService.save(worker);
        return workersMapper.workerToWorkerDto(worker);
    }


    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public List<WorkerDto> readAll(){
        List<Worker> workerList = workersService.readAll();
        return workersMapper.workerListToWorkerDtoList(workerList);
    }

    @DeleteMapping(
            path="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public WorkerDto delete(@PathVariable Long id){
        Worker worker = workersService.delete(id);
        return workersMapper.workerToWorkerDto(worker);
    }
}
