package com.example.repo;

import com.example.domain.Course;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CourseQueryDslRepo extends CrudRepository<Course, Integer>,
        QuerydslPredicateExecutor<Course> {
}
