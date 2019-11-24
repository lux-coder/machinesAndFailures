package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
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

    public Failure() {}

    public Failure(Long id, String title, String description, Boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

}
