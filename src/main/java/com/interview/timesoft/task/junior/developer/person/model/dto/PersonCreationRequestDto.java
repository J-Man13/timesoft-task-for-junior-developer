package com.interview.timesoft.task.junior.developer.person.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.timesoft.task.junior.developer.person.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreationRequestDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("sex")
    private Sex sex;
    @JsonProperty("age")
    private Long age;
}
