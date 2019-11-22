package com.example.machinesAndFailures.model.dao;

import com.example.machinesAndFailures.model.Failure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FailureRepository extends JpaRepository<Failure, Long> {

    List<Failure> findByTitle(String title);

    @Query("SELECT f FROM Failure f order by f.timestamp DESC")
    public List<Failure> findAll();

    @Query("SELECT f FROM Failure f WHERE f.id=:x")
    public Failure findFailureById(@Param("x") Long id);

    @Query("SELECT f FROM Failure f WHERE f.machine=:name order by f.timestamp DESC")
    public List<Failure> findFailureByMachineName(@Param("name") String name);

    @Modifying
    @Query("DELETE Failure WHERE id=:x")
    public void deleteFailureById(@Param("x") Long id);
}
