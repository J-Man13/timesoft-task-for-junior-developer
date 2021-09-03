package com.interview.timesoft.task.junior.developer.position.controller;

import com.interview.timesoft.task.junior.developer.position.mapper.WorkPositionsMapper;
import com.interview.timesoft.task.junior.developer.position.model.business.WorkPosition;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkPositionCreationRequestDto;
import com.interview.timesoft.task.junior.developer.position.model.dto.WorkPositionDto;
import com.interview.timesoft.task.junior.developer.position.service.WorkPositionsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/positions")
public class WorkPositionsController {

    private final WorkPositionsService workPositionsService;
    private final WorkPositionsMapper workPositionsMapper;


    public WorkPositionsController(final @Qualifier("workPositionsDbService") WorkPositionsService workPositionsService,
                                   final WorkPositionsMapper workPositionsMapper) {
        this.workPositionsService = workPositionsService;
        this.workPositionsMapper = workPositionsMapper;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public WorkPositionDto create(@RequestBody WorkPositionCreationRequestDto workPositionCreationRequestDto){
        WorkPosition workPosition = WorkPosition.builder().name(workPositionCreationRequestDto.getName()).build();
        workPosition = workPositionsService.save(workPosition);
        return workPositionsMapper.workPositionToWorkPositionDto(workPosition);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public List<WorkPositionDto> readAll(){
        List<WorkPosition> workPositionList = workPositionsService.readAll();
        return workPositionsMapper.workPositionListToWorkPositionDtoList(workPositionList);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public WorkPositionDto update(@RequestBody WorkPositionDto workPositionDto){
        WorkPosition workPosition = workPositionsMapper.workPositionDtoToWorkPosition(workPositionDto);
        workPosition = workPositionsService.update(workPosition);
        return workPositionsMapper.workPositionToWorkPositionDto(workPosition);
    }

    @DeleteMapping(
            path="/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(value = HttpStatus.OK)
    public WorkPositionDto delete(@PathVariable Long id){
        WorkPosition workPosition = workPositionsService.delete(id);
        return workPositionsMapper.workPositionToWorkPositionDto(workPosition);
    }
}
