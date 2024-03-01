package com.example.business;

import com.example.dao.CourseDao;
import com.example.domain.Course;
import com.example.repo.CourseRepo;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class DynamicQueryService {

    private CourseRepo courseRepo;

    public DynamicQueryService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
    public List<Course> filterBySpecification(CourseFilter filter){
        return courseRepo.findAll(filter.getSpecification());
    }
}
