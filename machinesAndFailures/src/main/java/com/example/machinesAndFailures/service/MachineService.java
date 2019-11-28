package com.example.machinesAndFailures.service;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;

import java.util.List;

public interface MachineService {

    public Machine saveMachine(String name, String type, String manufacturer, List<Failure> failures);

    public List<Machine> listMachine();

//    public Machine listAllFailures(Long id);

    public Machine getMachineById(Long id);

    public Machine getMachineByName(String name);

    public Machine deleteMachine(Machine machine);
}
