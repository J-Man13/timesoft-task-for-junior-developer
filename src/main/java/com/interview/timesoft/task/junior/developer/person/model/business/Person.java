package com.interview.timesoft.task.junior.developer.person.model.business;

import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.enumeration.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private String surname;
    private Sex sex;
    private Long age;
    private List<Skill> skillList;
    private EmploymentStatus employmentStatus;
    private LocalDateTime created;
    private LocalDateTime updated;
}
