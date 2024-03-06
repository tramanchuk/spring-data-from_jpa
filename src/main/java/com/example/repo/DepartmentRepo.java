package com.example.repo;

import com.example.domain.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.List;

public interface DepartmentRepo extends MongoRepository<Department, Integer> {

    Optional<Department> findByName(String name);
    List<Department> findByChairId(String chairId);
    @Query("{ 'name' : { $regex: ?0 } }")
    List<Department> findNameByPattern(String pattern);
}
