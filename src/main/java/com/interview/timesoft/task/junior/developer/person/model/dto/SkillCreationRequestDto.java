package com.interview.timesoft.task.junior.developer.person.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillCreationRequestDto {
    @JsonProperty("skillName")
    private String skillName;
    @JsonProperty("personId")
    private Long personId;
}
