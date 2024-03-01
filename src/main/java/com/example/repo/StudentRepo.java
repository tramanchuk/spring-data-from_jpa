package com.example.repo;

import com.example.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Integer> {
    List<Student> findByFullTime(boolean fullTime);

    List<Student> findByAge(Integer age);

    List<Student> findByAttendeeLastName(String lastName);


    @Query(value = "SELECT * FROM Student ORDER BY age DESC LIMIT 1", nativeQuery = true)
    Optional<Student> findOldest();

    @Query("SELECT s FROM Student s WHERE s.attendee.firstName = :firstName and s.attendee.lastName = :lastName")
    List<Student> findByFirstAndLastName(@Param("firstName") String firstName,
                                         @Param("lastName") String lastName);

    @Query("SELECT s FROM Student s where s.age < :age")
    List<Student> findByAgeLessThan(@Param("age") int age);

    @Query("SELECT s FROM Student s where s.attendee.lastName like :nameCriteria")
    List<Student> findSimilarName(@Param("nameCriteria") String nameCriteria);

    @Query(value = "Select * FROM Student ORDER BY s.last_name ASC LIMIT 1", nativeQuery = true)
    Optional<Student> findFirstInAlphabet();

    @Query(value = "SELECT * FROM Student ORDER BY age DESC LIMIT 3", nativeQuery = true)
    List<Student> find3Oldest();
}
