package com.interview.timesoft.task.junior.developer.person.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "skills")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.Constant.id)
    private Long id;

    @Column(name = ColumnName.Constant.created)
    private LocalDateTime created;

    @Column(name = ColumnName.Constant.updated)
    private LocalDateTime updated;

    @Column(name = "name",length = 300,nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity personEntity;

    public enum ColumnName{
        id(Constant.id),
        created(Constant.created),
        name(Constant.name),
        updated(Constant.updated);

        ColumnName(String columnName) {
            if(!columnName.equals(this.name()))
                throw new IllegalArgumentException();
        }

        public static class Constant {
            public static final String id = "id";
            public static final String created = "created";
            public static final String updated = "updated";
            public static final String name = "name";
        }
    }
}
