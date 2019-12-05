package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.*;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @GetMapping("/listf")
    public List<MachineFailuresCount> getMachineListf(){
        LOG.info("In /machine/listf");

        List<MachineFailuresCount> machineFailuresCounts = new ArrayList<>();

        List<Machine> machines = machineService.listMachine();
        List<Integer> failuresCount = machineService.failuresCount();
        LOG.info(String.valueOf(machines));
        LOG.info(String.valueOf(failuresCount));

        for(int i = 0; i< machines.size(); ++i ){
            MachineFailuresCount machineFailuresCount = new MachineFailuresCount();
            LOG.info(String.valueOf(i));
            machineFailuresCount.setMachine(machines.get(i));
            machineFailuresCount.setFailureCount(failuresCount.get(i));

            LOG.info(String.valueOf(machineFailuresCount));

            machineFailuresCounts.add(machineFailuresCount);

        }

        LOG.info(String.valueOf(machineFailuresCounts));

        return machineFailuresCounts;
    }


//    @GetMapping("/listf")
//    public Map<Machine, Integer> getMachineListf(){
//        LOG.info("In /machine/listf");
//
//        List<Machine> machines = machineService.listMachine();
//        List<Integer> failuresCount = machineService.failuresCount();
//        LOG.info(String.valueOf(machines));
//        LOG.info(String.valueOf(failuresCount));
//
//        HashMap<Machine, Integer> machineIntegerHashMap = new HashMap<>();
//
////        for(Machine machine: machines){
////            LOG.info(machine.getName());
////
////            machineIntegerHashMap.put(machine, failuresCount.iterator())
////        }
//
//        for(int i = 0; i < machines.size(); i++){
//            machineIntegerHashMap.put(machines.get(i), failuresCount.get(i));
//        }
//
//        LOG.info(String.valueOf(machineIntegerHashMap));
//
////        machineIntegerHashMap.
//
//
//        return machineIntegerHashMap;
//        //return zipToMap(machines, failuresCount);
//        }


        public static <Machine, Integer> Map<Machine, Integer> zipToMap(List<Machine> keys, List<Integer> values) {
            Iterator<Machine> keyIter = keys.iterator();
            Iterator<Integer> valIter = values.iterator();
            return IntStream.range(0, keys.size()).boxed()
                    .collect(Collectors.toMap(_i -> keyIter.next(), _i -> valIter.next()));
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
        if(failures != null) {
            LOG.info(String.valueOf(failures));
            for(Failure failure: failures){
                LOG.info("Title: {} and Description: {}",failure.getTitle(), failure.getDescription());
            }

        }

        List<FailureFile> failureFiles = requestWrapper.getFailureFiles();
//        if (failureFiles != null) {
//            LOG.info(String.valueOf(failureFiles));
//            for(FailureFile failureFile: failureFiles){
//                LOG.info("File name: {} and binary content: {}", failureFile.getFileName(), failureFile.getContent());
//            }
//        }


//        MultipartFile multipartFile = requestWrapper.getFiles();
//        LOG.info(String.valueOf(multipartFile));


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
            machineService.saveMachine(name, type, manufacturer, failures, failureFiles);
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

