package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/machine")
public class MachineController {

    private static final Logger LOG = LoggerFactory.getLogger(MachineController.class);

    @Autowired
    private MachineService machineService;

    @Autowired
    private FailureService failureService;


    @GetMapping("/list")
    public List<Machine> getMachineList(){
        LOG.info("In /machine/list");
        return machineService.listMachine();
    }

//    @GetMapping("/listFailures/{id}")
//    public Machine getAllFailures(@PathVariable("id") Long id){
//        LOG.info("In Machine /listFailures/{id}");
//        return machineService.listAllFailures(id);
//    }

    @GetMapping("/getMachineById/{machineId}")
    public Machine getMachineById(@PathVariable("machineId") Long id){
        return machineService.getMachineById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveMachine(@RequestBody HashMap<String, String> request){

        LOG.info("In Machine /save");

        String name = request.get("name");
        String type = request.get("type");
        String manufacturer = request.get("manufacturer");

        try {
            Machine machine = machineService.saveMachine(name, type, manufacturer);
            return new ResponseEntity<>(machine, HttpStatus.CREATED);

        } catch (Exception e){
            LOG.error("Exception {} occurred while trying to save machine with stacktrace {} ", e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Error occurred while trying to save machine", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMachine(@PathVariable("id") Long id){
        Machine machine = machineService.getMachineById(id);

        if(machine == null){
            return new ResponseEntity<>("Machine not found", HttpStatus.NOT_FOUND);
        }
        try {
            machineService.deleteMachine(machine);
            return new ResponseEntity<>("Machine deleted", HttpStatus.OK);

        } catch (Exception e){
            LOG.error("Exception {} occurred while trying to delete machine with stacktrace {} ", e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Exception occurred while trying to delete machine", HttpStatus.BAD_REQUEST);
        }
    }

//
//    @GetMapping("/getMachineFailures/{name}")
//    public ResponseEntity<?> getMachineFailures(@PathVariable("name") String name){
//        Machine machine = machineService.getMachineByName(name);
//
//        if(machine == null){
//            return new ResponseEntity<>("Machine not found", HttpStatus.NOT_FOUND);
//        }
//
//        try {
//            List<Failure> failures = failureService.getFailureByMachineName(name);
//            return new ResponseEntity<>(failures, HttpStatus.OK);
//
//        } catch (Exception e){
//            LOG.error("Exception {} occurred while trying to fetch machine failures with stacktrace {} ", e.getMessage(), e.getStackTrace());
//            return new ResponseEntity<>("Exception occurred while trying to fetch machine failures", HttpStatus.BAD_REQUEST);
//
//        }
//}





}

