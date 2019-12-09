package com.example.machinesAndFailures.service.impl;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.FailureFile;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.dao.FailureRepository;
import com.example.machinesAndFailures.model.dao.MachineRepository;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class MachineServiceImpl implements MachineService {

    private static final Logger LOG = LoggerFactory.getLogger(MachineServiceImpl.class);

    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private FailureRepository failureRepository;

    @Override
    public Machine saveMachine(String name, String type, String manufacturer, List<Failure> failures, List<FailureFile> failureFiles) {
        LOG.info("In saveMachine");
        Machine machine = new Machine();
        machine.setName(name);
        machine.setType(type);
        machine.setManufacturer(manufacturer);

        machine.setFailures(failures);

        for(Failure failure: failures){
            failure.setMachine(machine);
            failure.setTimestamp(new Timestamp(System.currentTimeMillis()));
            LOG.info(String.valueOf(failure.getMachine()));

            if(failureFiles != null){
            failure.setFailureFiles(failureFiles);

            for(FailureFile failureFile: failureFiles){
                failureFile.setFailure(failure);
                LOG.info(String.valueOf(failureFile.getFailure()));
            }
            failureRepository.save(failure);
            }
        }
        machineRepository.save(machine);
        return machine;
    }

    @Override
    public List<Machine> listMachine() {
        return machineRepository.findAll();
    }

//    @Override
//    public Map<Machine, Integer> listMachineF() {
//
//        return machineRepository.listMachineF();
//
//    }

    @Override
    public List<Integer> failuresCount() {
        return machineRepository.listMachineF();
    }

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
