package com.interview.timesoft.task.junior.developer.person.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PERSONS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.Constant.id)
    private Long id;

    @Column(name = SkillEntity.ColumnName.Constant.created)
    private LocalDateTime created;

    @Column(name = SkillEntity.ColumnName.Constant.updated)
    private LocalDateTime updated;

    @Column(name = ColumnName.Constant.name,nullable = false, length = 300)
    private String name;

    @Column(name = ColumnName.Constant.surname,nullable = false, length = 300)
    private String surname;

    @Column(name = ColumnName.Constant.sex,nullable = false, length = 30)
    private String sex;

    @Column(name = ColumnName.Constant.age,nullable = false)
    private Long age;

    @Column(name = ColumnName.Constant.employmentStatus,length = 30)
    private String employmentStatus;

    @OneToMany(
            mappedBy = "personEntity",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private List<SkillEntity> skillEntityList;

    public enum ColumnName {
        id(Constant.id),
        created(Constant.created),
        updated(Constant.updated),
        name(Constant.name),
        surname(Constant.surname),
        sex(Constant.sex),
        age(Constant.age),
        employment_status(Constant.employmentStatus);

        ColumnName(String columnName) {
            if(!columnName.equals(this.name()))
                throw new IllegalArgumentException();
        }

        public static class Constant {
            public static final String id = "id";
            public static final String created = "created";
            public static final String updated = "updated";
            public static final String name = "name";
            public static final String surname = "surname";
            public static final String sex = "sex";
            public static final String age = "age";
            public static final String employmentStatus = "employmentStatus";
        }
    }
}