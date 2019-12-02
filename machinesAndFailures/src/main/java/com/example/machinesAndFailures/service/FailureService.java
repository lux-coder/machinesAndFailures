package com.example.machinesAndFailures.service;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.Priority;

import java.util.List;

public interface FailureService {

    public Failure saveFailure(Machine machine, String title, String description, Priority priority);

    public List<Failure> listFailure();

    public List<Failure> listFailureWithMachine(String machineName);

    public List<Failure> listThemAll();

    public List<Failure> listFailureUnresolved();

    public Integer hasUnresolvedFailures(String machineName);

    public Failure getFailureById(Long id);

    public void updateFailureStatus(Long id);

//    public List<Failure> getFailureByMachineName(String name);

    public Failure deleteFailure(Failure failure);
}
