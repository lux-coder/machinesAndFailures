package com.example.machinesAndFailures.service;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;

import java.util.List;

public interface FailureService {

    public Failure saveFailure(Machine machine, String title, String description);

    public List<Failure> listFailure();

    public Failure getFailureById(Long id);

//    public List<Failure> getFailureByMachineName(String name);

    public Failure deleteFailure(Failure failure);
}
