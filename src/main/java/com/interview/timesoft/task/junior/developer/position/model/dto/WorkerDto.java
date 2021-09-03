package com.interview.timesoft.task.junior.developer.position.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.timesoft.task.junior.developer.person.model.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("person")
    private PersonDto person;
}
