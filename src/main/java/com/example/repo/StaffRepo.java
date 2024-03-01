package com.example.repo;

import com.example.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Integer> {
}
