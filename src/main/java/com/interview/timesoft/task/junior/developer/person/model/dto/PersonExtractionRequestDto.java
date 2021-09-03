package com.interview.timesoft.task.junior.developer.person.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.enumeration.Sex;
import com.interview.timesoft.task.junior.developer.person.model.entity.PersonEntity;
import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonExtractionRequestDto {
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
    @JsonProperty("employmentStatus")
    private EmploymentStatus employmentStatus;

    @JsonProperty("pagingFrom")
    private Integer pagingFrom;
    @JsonProperty("pagingTo")
    private Integer pagingTo;
    @JsonProperty("ascending")
    private Boolean ascending;
    @JsonProperty("orderByParameter")
    private OrderByParameter orderByParameter;

    public static enum OrderByParameter{
        id,
        created,
        updated,
        name,
        surname,
        sex,
        age,
        employmentStatus;
    }

}
