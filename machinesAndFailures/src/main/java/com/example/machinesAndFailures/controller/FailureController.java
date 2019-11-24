package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

//    @GetMapping("/list")
//    public List<Failure> getFailuresList(){
//
//        LOG.info("In failure/list");

//        List<Failure> failures = new ArrayList<>();
//        failures.add(new Failure((long)5, "testing", "testinggg", true));
//        failures.add(new Failure((long)6, "testing", "testinggg", true));
//        failures.add(new Failure((long)7, "testing", "testinggg", true));
//
//        return failures;
//    }





}
