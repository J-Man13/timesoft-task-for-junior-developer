package com.interview.timesoft.task.junior.developer.position.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.interview.timesoft.task.junior.developer.position.model.business.WorkPositionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkPositionDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private WorkerDto worker;

    private WorkPositionStatus workPositionStatus;
}
