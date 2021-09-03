package com.interview.timesoft.task.junior.developer.person.model.business;

import com.interview.timesoft.task.junior.developer.person.enumeration.EmploymentStatus;
import com.interview.timesoft.task.junior.developer.person.enumeration.Sex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonExtractionFilter {
    private Long id;
    private String name;
    private String surname;
    private Sex sex;
    private Long age;
    private EmploymentStatus employmentStatus;

    private Integer pagingFrom;
    private Integer pagingTo;
    private Boolean ascending;
    private OrderByParameter orderByParameter;

    public enum OrderByParameter{
        id,
        created,
        updated,
        name,
        surname,
        sex,
        age,
        employmentStatus
    }

    public enum ConstantDefault {
        pagingFrom(Field.pagingFromString),
        pagingTo(Field.pagingToString),
        ascending(Field.ascendingString),
        orderByParameter(Field.orderByParameterString);

        ConstantDefault(String fieldName) {
            if(!fieldName.equals(this.name()))
                throw new IllegalArgumentException();
        }

        public static class Field {
            public static final Integer pagingFrom = 0;
            public static final String pagingFromString = "0";
            public static final Integer pagingTo = 10;
            public static final String pagingToString = "10";
            public static final Boolean ascending = false;
            public static final String ascendingString = "false";
            public static final OrderByParameter orderByParameter = OrderByParameter.created;
            public static final String orderByParameterString = "created";
        }
    }
}
