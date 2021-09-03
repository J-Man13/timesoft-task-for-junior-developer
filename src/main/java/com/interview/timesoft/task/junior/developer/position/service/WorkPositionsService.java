package com.interview.timesoft.task.junior.developer.position.service;

import com.interview.timesoft.task.junior.developer.position.model.business.WorkPosition;
import com.interview.timesoft.task.junior.developer.position.model.business.Worker;

import java.util.List;

public interface WorkPositionsService {
    WorkPosition save(WorkPosition workPosition);
    List<WorkPosition> readAll();
    WorkPosition read(Long id);
    WorkPosition delete(Long id);
    WorkPosition update(WorkPosition workPosition);
}
