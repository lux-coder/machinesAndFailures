package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineFailuresCount {

    private Machine machine;
    private Integer failureCount;
}
