package com.example.business;

import com.example.domain.Course;
import com.example.repo.CourseQueryDslRepo;
import com.example.repo.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    private CourseQueryDslRepo queryDslRepo;

    public DynamicQueryService(CourseRepo courseRepo, CourseQueryDslRepo queryDslRepo) {
        this.courseRepo = courseRepo;
        this.queryDslRepo = queryDslRepo;
    }
    public List<Course> filterBySpecification(CourseFilter filter){
        return courseRepo.findAll(filter.getSpecification());
    }
    public List<Course> filterByQueryDsl(CourseFilter filter){
        List<Course> courses = new ArrayList<>();
        queryDslRepo
                .findAll(filter.getQueryDslPredicate())
                .forEach(courses::add);
        return courses;
    }

    public List<Course> filterByExample(CourseFilter filter){
        return courseRepo.findAll(filter.getExampleProbe());
    }
}
