package com.example.machinesAndFailures.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity(name = "Failure")
@Table(name = "failure")
public class Failure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private Boolean status;
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machineName", referencedColumnName = "name")
    private Machine machineName;

    private Priority priority;

    @OneToMany(
            mappedBy = "failureTitle",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<FailureFile> failureFiles = new ArrayList<>();

    public Failure() {}

    public Failure(Long id, String title, String description, Boolean status, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
    }

    public Failure(Long id, String title, String description, Boolean status, Timestamp timestamp, Machine machineName, Priority priority, List<FailureFile> failureFiles) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.timestamp = timestamp;
        this.machineName = machineName;
        this.priority = priority;
        this.failureFiles = failureFiles;
    }

    public String getMachineName(){
        return machineName.getName();
    }

    @JsonIgnore
    public Machine getMachine(){
        return machineName;
    }

    @JsonIgnore
    public void setMachine(Machine machineName){
        this.machineName = machineName;
    }


    public void addFailureFile(FailureFile failureFile){
        failureFiles.add(failureFile);
        failureFile.setFailureTitle(this);
    }

    public void removeFailureFiles(FailureFile failureFile){
        failureFiles.remove(failureFile);
        failureFile.setFailureTitle(null);
    }
//
//    @Override
//    public String toString() {
//        return "Failure{" +
//                "id=" + id +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", status=" + status +
//                ", timestamp=" + timestamp +
//                ", machineName=" + machineName +
//                ", priority=" + priority +
//                ", failureFiles=" + failureFiles +
//                '}';
//    }
}
