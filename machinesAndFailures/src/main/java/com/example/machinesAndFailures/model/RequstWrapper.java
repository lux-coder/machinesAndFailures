package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequstWrapper {

    private Machine machine;
    private List<Failure> failures;
}
