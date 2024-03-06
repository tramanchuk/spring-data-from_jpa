package com.example.repo;

import com.example.domain.Staff;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface StaffRepo extends ReactiveCrudRepository<Staff, String> {

    Flux<Staff> findByMemberLastName(String lastName);

}
