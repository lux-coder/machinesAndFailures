package com.example.machinesAndFailures.config;

import com.example.machinesAndFailures.model.dao.MachineRepository;
import com.example.machinesAndFailures.service.FailureService;
import com.example.machinesAndFailures.service.MachineService;
import com.example.machinesAndFailures.service.impl.FailureServiceImpl;
import com.example.machinesAndFailures.service.impl.MachineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class ApplicationEnvironment {

    public static final String CLIENT_DOMAIN_URL = "*";

    @Autowired
    DataSource dataSource;



    @Bean
    MachineService machineService(){
        return new MachineServiceImpl();
    }

    @Bean
    FailureService failureService(){
        return new FailureServiceImpl();
    }

}
