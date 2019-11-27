package com.example.machinesAndFailures.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

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

    public Failure() {}

    public Failure(Long id, String title, String description, Boolean status, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
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

}
