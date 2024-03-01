package com.example.repo;

import com.example.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
