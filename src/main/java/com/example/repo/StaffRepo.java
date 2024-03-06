package com.example.repo;

import com.example.domain.Staff;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StaffRepo extends CrudRepository<Staff, Integer>, PagingAndSortingRepository<Staff, Integer> {

    List<Staff> findByMemberLastName(String lastName);
    @Query("{ 'member.firstName' : ?0 }")
    List<Staff> findByFirstName(String lastName);

}
