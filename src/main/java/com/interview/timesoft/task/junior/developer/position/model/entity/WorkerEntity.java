package com.interview.timesoft.task.junior.developer.position.model.entity;

import com.interview.timesoft.task.junior.developer.person.model.entity.PersonEntity;
import com.interview.timesoft.task.junior.developer.person.model.entity.SkillEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WORKER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.Constant.id)
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private PersonEntity personEntity;

    @Column(name = ColumnName.Constant.created)
    private LocalDateTime created;

    @Column(name = ColumnName.Constant.updated)
    private LocalDateTime updated;

    public enum ColumnName{
        id(ColumnName.Constant.id),
        created(ColumnName.Constant.created),
        name(ColumnName.Constant.name),
        updated(ColumnName.Constant.updated);

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
