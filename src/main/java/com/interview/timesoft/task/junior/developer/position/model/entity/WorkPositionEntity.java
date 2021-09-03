package com.interview.timesoft.task.junior.developer.position.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WORK_POSITION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ColumnName.Constant.id)
    private Long id;

    @Column(name = ColumnName.Constant.name, nullable = false)
    private String name;

    @Column(name = ColumnName.Constant.created, nullable = false)
    private LocalDateTime created;

    @Column(name = ColumnName.Constant.updated)
    private LocalDateTime updated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id")
    private WorkerEntity workerEntity;

    @Column(name = ColumnName.Constant.workPositionStatus, nullable = false)
    private String workPositionStatus;


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
            public static final String workPositionStatus = "workPositionStatus";
        }
    }
}
