package com.example.machinesAndFailures.controller;

import com.example.machinesAndFailures.model.FailureFile;
import com.example.machinesAndFailures.model.UploadFileResponse;
import com.example.machinesAndFailures.service.FailureFileService;
import javassist.bytecode.ByteArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.core.io.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FailureFileController {
    private static final Logger LOG = LoggerFactory.getLogger(FailureFileController.class);

    @Autowired
    FailureFileService failureFileService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile (@RequestParam("file") MultipartFile file) {
        LOG.info("In /uploadFile");
        FailureFile failureFile = failureFileService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile")
                .path(String.valueOf(failureFile.getId()))
                .toUriString();

        return new UploadFileResponse(failureFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }


    @PostMapping("/uploadFiles")
    public List<UploadFileResponse> uploadFiles(@RequestParam("files")MultipartFile[] files){
        LOG.info("In /uploadFiles");

        for(MultipartFile file: files){
            LOG.info("File name {}", file.getName());
        }

        for (int i=0; i<files.length; i++){
            LOG.info(String.valueOf(files.length));
            LOG.info(files[i].getOriginalFilename());
        }

        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/getFile/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable Long id){
        FailureFile failureFile = failureFileService.getFileById(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(failureFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + failureFile.getFileName() + "\"")
                .body(new ByteArrayResource(failureFile.getContent()));
    }

}
