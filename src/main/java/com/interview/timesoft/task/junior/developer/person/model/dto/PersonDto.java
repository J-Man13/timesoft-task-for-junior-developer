package com.interview.timesoft.task.junior.developer.person.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("sex")
    private Sex sex;
    @JsonProperty("age")
    private Long age;
    @JsonProperty("skills")
    private List<PersonSkillDto> personSkillDtoList;
    @JsonProperty("employmentStatus")
    private EmploymentStatus employmentStatus;
}
