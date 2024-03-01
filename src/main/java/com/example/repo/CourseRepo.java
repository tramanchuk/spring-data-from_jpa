package com.example.repo;

import com.example.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, Integer> {
    Optional<Course> findByName(String name);
    List<Course> findCourseByPrerequisites(int id);

    List<Course> findByCredits(int credits);

    List<Course> findByDepartmentChairMemberLastName(String chair);
}
