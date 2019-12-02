package com.example.machinesAndFailures.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
//@ToString
@Entity(name = "Machine")
@Table(name = "machine")
public class Machine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    private String manufacturer;

    @OneToMany(
            mappedBy = "machineName",
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
        failure.setMachineName(this);
    }

    //added for mitigating propagation issues
    public void removeFailure(Failure failure){
        failures.remove(failure);
        failure.setMachineName(null);
    }

//    @Override
//    public String toString() {
//        return "Machine{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                ", manufacturer='" + manufacturer + '\'' +
//                ", failures=" + Arrays.toString(failures.toArray()) +
//                '}';
//    }
}
