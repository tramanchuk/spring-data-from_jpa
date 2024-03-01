package com.example.repo;

import com.example.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(String name);
}
