package com.example.machinesAndFailures.service;

import com.example.machinesAndFailures.model.FailureFile;

import java.io.File;
import java.util.List;

public interface FailureFileService {

    public FailureFile saveFile(String fileName, File content);

    public List<FailureFile> listFailureFiles();

    public FailureFile getFileByName(String fileName);

    public FailureFile getFileById(Long id);

    public void deleteFile(FailureFile failureFile);
}
