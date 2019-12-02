package com.example.machinesAndFailures.model.dao;

import com.example.machinesAndFailures.model.Failure;
import com.example.machinesAndFailures.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {

//    @Query("SELECT f FROM Failure f WHERE f.machine.id=:x")
//    public Machine findAllFailures(@Param("x") Long id);

    @Query("SELECT m FROM Machine m WHERE m.id=:x")
    public Machine findMachineById(@Param("x") Long id);

    @Modifying
    @Query("DELETE Machine WHERE id=:x")
    public void deleteMachineById(@Param("x") Long id);

    @Query("SELECT m FROM Machine m WHERE m.name=:x")
    public Machine findMachineByName(@Param("x") String name);

    @Query(value = "SELECT COUNT(*) FROM failure WHERE status=false GROUP BY machine_name", nativeQuery = true)
    public List<Integer> listMachineF();
}
