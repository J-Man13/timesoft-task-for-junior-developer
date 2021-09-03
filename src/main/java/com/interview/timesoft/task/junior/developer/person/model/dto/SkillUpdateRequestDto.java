package com.interview.timesoft.task.junior.developer.person.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillUpdateRequestDto{
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
}
