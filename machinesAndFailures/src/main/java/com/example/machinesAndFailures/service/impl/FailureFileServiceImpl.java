package com.example.machinesAndFailures.service.impl;

import com.example.machinesAndFailures.model.FailureFile;
import com.example.machinesAndFailures.service.FailureFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class FailureFileServiceImpl implements FailureFileService {

    private static final Logger LOG = LoggerFactory.getLogger(FailureFileServiceImpl.class);


    @Override
    public FailureFile saveFile(String fileName, File content) {
        return null;
    }

    @Override
    public List<FailureFile> listFailureFiles() {
        return null;
    }

    @Override
    public FailureFile getFileByName(String fileName) {
        return null;
    }

    @Override
    public FailureFile getFileById(Long id) {
        return null;
    }

    @Override
    public void deleteFile(FailureFile failureFile) {

    }
}
