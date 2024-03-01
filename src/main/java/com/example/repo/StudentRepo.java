package com.example.repo;

import com.example.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.TypedQuery;
import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFullTime(boolean fullTime);

    List<Student> findByAge(Integer age);

    List<Student> findByAttendeeLastName(String lastName);
}
