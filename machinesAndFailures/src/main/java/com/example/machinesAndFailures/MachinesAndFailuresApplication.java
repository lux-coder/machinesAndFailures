package com.example.machinesAndFailures;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import com.example.machinesAndFailures.model.dao.MachineRepository;
import com.example.machinesAndFailures.service.MachineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MachinesAndFailuresApplication {

	private static final Logger LOG = LoggerFactory.getLogger(MachinesAndFailuresApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MachinesAndFailuresApplication.class, args);

		LOG.info("Application started...");


	}
}
