package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class RequestWrapper {

    private Machine machine;
    private List<Failure> failures;
    private MultipartFile files;
}
