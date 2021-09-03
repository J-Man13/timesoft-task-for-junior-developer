package com.interview.timesoft.task.junior.developer.person.model.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Skill {
    private Long id;
    private String name;
    private Person person;
    private LocalDateTime created;
    private LocalDateTime updated;
}
