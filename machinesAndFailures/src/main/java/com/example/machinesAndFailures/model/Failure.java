package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity(name = "Failure")
@Table(name = "failure")
public class Failure {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private Boolean status;
    private Timestamp timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private Machine machine;

    public Failure() {}

    public Failure(Long id, String title, String description, Boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Failure(Long id, String title, String description, Boolean status, Machine machine) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.machine = machine;
    }
}
