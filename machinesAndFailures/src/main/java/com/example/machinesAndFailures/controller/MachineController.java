package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.RequestWrapper;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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
    public ResponseEntity<?> saveMachine(@RequestBody RequestWrapper requestWrapper) {
    //public ResponseEntity<?> saveMachine(@RequestBody Machine requestWrapper) {
        LOG.info("In Machine /save");

        LOG.info(String.valueOf(requestWrapper));

        Machine machine = requestWrapper.getMachine();
        LOG.info(String.valueOf(machine));

        List<Failure> failures = requestWrapper.getFailures();
        LOG.info(String.valueOf(failures));

//        MultipartFile multipartFile = requestWrapper.getFiles();
//        LOG.info(String.valueOf(multipartFile));

        for(Failure failure: failures){
            LOG.info("Title: {} and Description: {}",failure.getTitle(), failure.getDescription());
        }

//        for(MultipartFile multipartFile: multipartFiles){
//            LOG.info("File name: {} and file size {}", multipartFile.getName(), multipartFile.getSize());
//        }


        String name = machine.getName();
        String type = machine.getType();
        String manufacturer = machine.getManufacturer();
//        failures = machine.getFailures();

//
//        LOG.info("Name: {}", name);
//        LOG.info("Type: {}", type);
//        LOG.info("Manufacturer: {}", manufacturer);
//        LOG.info("Failures: {}", failures);
//        LOG.info("Machine object: {}", machine);
//
        try {
            machineService.saveMachine(name, type, manufacturer, failures);
            return new ResponseEntity<>("Machine saved", HttpStatus.CREATED);

        } catch (Exception e){
            LOG.error("Exception {} occurred while trying to save machine with stacktrace {} ", e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Error occurred while trying to save machine", HttpStatus.BAD_REQUEST);
        }

//        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
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


}

