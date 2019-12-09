package com.example.machinesAndFailures.service.impl;

import com.example.machinesAndFailures.model.FailureFile;
import com.example.machinesAndFailures.model.dao.FailureFileRepository;
import com.example.machinesAndFailures.service.FailureFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FailureFileServiceImpl implements FailureFileService {

    private static final Logger LOG = LoggerFactory.getLogger(FailureFileServiceImpl.class);

    @Autowired
    private FailureFileRepository failureFileRepository;

    @Override
    public FailureFile saveFile(MultipartFile file){
        LOG.info("In saveFile service");

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid sequence " + fileName);
            }

            FailureFile failureFile = new FailureFile(fileName, file.getContentType(), file.getBytes());
            return failureFileRepository.save(failureFile);

        } catch (Exception e){
            LOG.error("Error while trying to save file to database {}, {}", e, e.getStackTrace());
            return null;
        }
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
    public FailureFile getFileById(Long id)  {
        return failureFileRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteFile(FailureFile failureFile) {

    }
}
