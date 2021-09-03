package com.interview.timesoft.task.junior.developer.position.model.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkPosition {
	private Long id;
	private String name;
	private LocalDateTime created;
	private Worker worker;
	private WorkPositionStatus workPositionStatus;
}
