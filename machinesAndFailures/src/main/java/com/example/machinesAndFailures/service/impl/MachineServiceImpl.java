package com.example.machinesAndFailures.service.impl;

import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.dao.MachineRepository;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MachineServiceImpl implements MachineService {

    private static final Logger LOG = LoggerFactory.getLogger(MachineServiceImpl.class);

    @Autowired
    private MachineRepository machineRepository;


    @Override
    public Machine saveMachine(String name, String type, String manufacturer) {
        Machine machine = new Machine();
        machine.setName(name);
        machine.setType(type);
        machine.setManufacturer(manufacturer);

        machineRepository.save(machine);
        return machine;
    }

    @Override
    public List<Machine> listMachine() {
        return machineRepository.findAll();
    }

//    @Override
//    public Machine listAllFailures(Long id) {
//        return machineRepository.findAllFailures(id);
//    }

    @Override
    public Machine getMachineById(Long id) {
        return machineRepository.findMachineById(id);
    }

    @Override
    public Machine getMachineByName(String name) {
        return machineRepository.findMachineByName(name);
    }

    @Override
    public Machine deleteMachine(Machine machine) {
        machineRepository.deleteById(machine.getId());
        return machine;
    }
}
