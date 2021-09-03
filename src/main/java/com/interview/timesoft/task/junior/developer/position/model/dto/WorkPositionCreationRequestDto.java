package com.interview.timesoft.task.junior.developer.position.model.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPositionCreationRequestDto {
    @JsonProperty("name")
    private String name;
}
