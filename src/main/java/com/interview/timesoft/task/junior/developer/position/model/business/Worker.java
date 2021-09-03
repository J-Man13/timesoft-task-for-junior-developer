package com.interview.timesoft.task.junior.developer.position.model.business;

import com.interview.timesoft.task.junior.developer.person.model.business.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Worker {
    private Long id;
    private Person person;
}
