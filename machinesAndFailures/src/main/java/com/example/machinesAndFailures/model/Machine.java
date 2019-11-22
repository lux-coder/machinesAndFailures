package com.example.machinesAndFailures.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity(name = "Machine")
@Table(name = "machine")
public class Machine {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private String manufacturer;

    @OneToMany(
            mappedBy = "machine",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Failure> failures = new ArrayList<>();

    public Machine() {}

    public Machine(Long id, String name, String type, String manufacturer) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public Machine(Long id, String name, String type, String manufacturer, List<Failure> failures) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
        this.failures = failures;
    }

    //added for mitigating propagation issues
    public void addFailure(Failure failure){
        failures.add(failure);
        failure.setMachine(this);
    }

    //added for mitigating propagation issues
    public void removeFailure(Failure failure){
        failures.remove(failure);
        failure.setMachine(null);
    }

}
