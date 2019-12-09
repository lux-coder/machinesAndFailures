package com.example.machinesAndFailures.service;

import com.example.machinesAndFailures.model.FailureFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FailureFileService {

    public FailureFile saveFile(MultipartFile file);

    public List<FailureFile> listFailureFiles();

    public FailureFile getFileByName(String fileName);

    public FailureFile getFileById(Long id);

    public void deleteFile(FailureFile failureFile);
}
