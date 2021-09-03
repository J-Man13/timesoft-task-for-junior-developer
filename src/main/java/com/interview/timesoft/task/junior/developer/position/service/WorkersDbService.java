package com.interview.timesoft.task.junior.developer.position.service;

import com.interview.timesoft.task.junior.developer.position.mapper.WorkersMapper;
import com.interview.timesoft.task.junior.developer.position.model.business.Worker;
import com.interview.timesoft.task.junior.developer.position.model.entity.WorkerEntity;
import com.interview.timesoft.task.junior.developer.position.repository.WorkersRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkersDbService implements WorkersService {
    private final WorkersRepository workersRepository;
    private final WorkersMapper workersMapper;

    public WorkersDbService(final WorkersRepository workersRepository,
                            final WorkersMapper workersMapper) {
        this.workersRepository = workersRepository;
        this.workersMapper = workersMapper;
    }

    @Override
    public Worker save(Worker worker) {
        WorkerEntity workerEntity = workersMapper.workerToWorkerEntity(worker);
        workerEntity.setCreated(LocalDateTime.now());
        workerEntity = workersRepository.save(workerEntity);
        return workersMapper.workerEntityToWorker(workerEntity);
    }

    @Override
    public List<Worker> readAll() {
        List<WorkerEntity> workerEntityList = workersRepository.findAll();
        return workersMapper.workerEntityListToWorkerList(workerEntityList);
    }

    @Override
    public Worker read(Long id) {
        WorkerEntity workerEntity = workersRepository.findById(id).orElseThrow(()->new NullPointerException("No worker found for read"));
        return workersMapper.workerEntityToWorker(workerEntity);
    }

    @Override
    public Worker delete(Long id) {
        WorkerEntity workerEntity = workersRepository.findById(id).orElseThrow(()->new NullPointerException("No worker found to delete"));
        workersRepository.delete(workerEntity);
        return workersMapper.workerEntityToWorker(workerEntity);
    }


}
