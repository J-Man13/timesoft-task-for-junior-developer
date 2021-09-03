package com.interview.timesoft.task.junior.developer.person.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("person")
    private PersonDto personDto;
}
