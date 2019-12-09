package com.example.machinesAndFailures.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;

@Getter
@Setter
@Entity(name = "FailureFile")
@Table(name = "failureFiles")
public class FailureFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "failureTitle", referencedColumnName = "title")
    private Failure failureTitle;

    public FailureFile() {
    }

    public FailureFile(String fileName, String fileType, byte[] content) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.content = content;
    }

    public String getFailureTitle(){
        return failureTitle.getTitle();
    }

    @JsonIgnore
    public Failure getFailure(){
        return failureTitle;
    }

    @JsonIgnore
    public void setFailure(Failure failureTitle){
        this.failureTitle = failureTitle;
    }
}
