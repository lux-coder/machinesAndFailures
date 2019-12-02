package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.Priority;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/failure")
public class FailureController {

    private static final Logger LOG = LoggerFactory.getLogger(FailureController.class);

    @Autowired
    private MachineService machineService;

    @Autowired
    private FailureService failureService;

    @GetMapping("/list")
    public List<Failure> getFailuresList(){
        LOG.info("In /list controller");
        return failureService.listFailure();
    }

    @GetMapping("/listWithMachine")
    public List<Failure> getFailuresListWithMachineName(@RequestBody HashMap<String, String> request){
        LOG.info("In /listWithMachine");
        String machineName = request.get("machineName");
        return failureService.listFailureWithMachine(machineName);
    }

    @GetMapping("/getThemAll")
    public List<Failure> getThemAll(){
        LOG.info("In /getThemAll");
        return failureService.listThemAll();
    }

    @GetMapping()
    public List<Failure> getFailuresUnresolved(){
        LOG.info("In /getFailuresUnresolved");
        return failureService.listFailureUnresolved();
    }

//    @GetMapping("/unresolved")
//    public Integer hasUnresolvedFailures(@RequestBody HashMap<String, String> request){
//        LOG.info("In hasUnresolvedFailures");
//        String machineName = request.get("machineName");
//        //return failureService.hasUnresolvedFailures(machineName);
//        return 5;
//    }


    @CrossOrigin(origins = "*", methods = RequestMethod.PUT, allowedHeaders = "*")
    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateFailureStatus(@RequestBody Long id){
        LOG.info("In /updateFailureStatus");

        if(failureService.getFailureById(id) != null ){
            try {
                LOG.info("Status set to true");
                failureService.updateFailureStatus(id);
                return new ResponseEntity<>("Status updated", HttpStatus.OK);

            } catch (Exception e){
                LOG.error("Exception {} occurred while trying to update status with stacktrace {}", e.getMessage(), e);
                return new ResponseEntity<>("Error setting status", HttpStatus.BAD_REQUEST);
            }
        } else
            return new ResponseEntity<>("No failure with that ID", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveFailure(@RequestBody HashMap<String, String> request){
        LOG.info("In /failure/save");
        String machineName = request.get("machineName");
        Machine machine = machineService.getMachineByName(machineName);

        LOG.info("Got machine {}", machine);

        String title = request.get("title");
        String description = request.get("description");
        String priority = request.get("priority");

        LOG.info("Got priority {}", priority);

        try {
            Failure failure = failureService.saveFailure(machine, title, description, Priority.valueOf(priority));
            return new ResponseEntity<>(failure, HttpStatus.OK);

        } catch (Exception e){
            LOG.error("Exception {} occurred while trying to save failure with stacktrace {} ", e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Error occurred while trying to save failure", HttpStatus.BAD_REQUEST);
        }
    }
}
