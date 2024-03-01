package com.example.dao;

import com.example.business.UniversityService;
import com.example.domain.Course;
import com.example.domain.Staff;
import com.example.repo.CourseRepo;
import com.example.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests that query by one attribute
 */
@SpringBootTest
public class FindByOneAttribute {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    private List<Staff> allStaff;
    @Test
    public void findByOneAttribute() {
        // Test Create
        UniversityFactory.fillUniversity(universityService);

        studentRepo.findByFullTime(true).stream().forEach(s -> assertTrue(s.isFullTime()));

        studentRepo.findByAge(20).stream().forEach(student -> assertTrue(student.getAge() == 20));

        studentRepo.findByAttendeeLastName("King").stream()
                .forEach(s -> assertTrue(s.getAttendee().getLastName().equals("King")));


        List<Course> allCourses = universityService.findAllCourses();
        Course firstCourse = allCourses.get(0);

        assertEquals(firstCourse, courseRepo.findByName(firstCourse.getName()).get());

        assertEquals(firstCourse.getDepartment().getChair().getMember().getLastName(),
                courseRepo.findByDepartmentChairMemberLastName(firstCourse.getDepartment().getChair().getMember().getLastName())
                    .get(0).getDepartment().getChair().getMember().getLastName());

        Course courseWithPrerequisites = allCourses.stream().filter(x->x.getPrerequisites().size() > 0).findFirst().get();
        Integer prerequisiteId = courseWithPrerequisites.getPrerequisites().get(0).getId();
        assertTrue(courseRepo.findCourseByPrerequisites(prerequisiteId).contains(courseWithPrerequisites));

        courseRepo.findByCredits(3).stream().forEach(x-> assertEquals(3, x.getCredits()));
    }
}
