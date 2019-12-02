package com.example.machinesAndFailures.model.dao;

import com.example.machinesAndFailures.model.FailureFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureFileRepository extends JpaRepository<FailureFile, Long> {
}
