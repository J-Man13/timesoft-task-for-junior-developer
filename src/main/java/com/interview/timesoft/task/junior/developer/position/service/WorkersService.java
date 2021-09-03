package com.interview.timesoft.task.junior.developer.position.service;

import com.interview.timesoft.task.junior.developer.position.model.business.Worker;

import java.util.List;

public interface WorkersService {
    Worker save(Worker worker);
    List<Worker> readAll();
    Worker delete(Long id);
    Worker read(Long id);
}
