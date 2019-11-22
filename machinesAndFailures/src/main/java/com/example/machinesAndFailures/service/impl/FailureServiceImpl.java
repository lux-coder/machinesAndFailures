package com.example.machinesAndFailures.service.impl;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.dao.FailureRepository;
import com.example.machinesAndFailures.service.FailureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class FailureServiceImpl implements FailureService {

    private static final Logger LOG = LoggerFactory.getLogger(FailureServiceImpl.class);

    @Autowired
    private FailureRepository failureRepository;

    @Override
    public Failure saveFailure(Machine machine, String title, String description) {
        String machineName = machine.getName();

        Failure failure = new Failure();
        failure.setMachine(machine);
        failure.setTitle(title);
        failure.setDescription(description);
        failure.setStatus(false);
        failure.setTimestamp(new Timestamp(System.currentTimeMillis()));

        machine.setFailures(Collections.singletonList(failure));

        failureRepository.save(failure);
        return failure;
    }

    @Override
    public List<Failure> listFailure() {
        return failureRepository.findAll();
    }

    @Override
    public Failure getFailureById(Long id) {
        return failureRepository.findFailureById(id);
    }

    @Override
    public List<Failure> getFailureByMachineName(String name) {
        return failureRepository.findFailureByMachineName(name);
    }

    @Override
    public Failure deleteFailure(Failure failure) {
        failureRepository.deleteFailureById(failure.getId());
        return failure;
    }
}
