package com.example.dao;

import com.example.business.CourseFilter;
import com.example.business.DynamicQueryService;
import com.example.business.UniversityService;
import com.example.domain.Department;
import com.example.domain.Person;
import com.example.domain.Staff;
import com.example.repo.CourseRepo;
import com.example.repo.DepartmentRepo;
import com.example.repo.StaffRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static com.example.business.CourseFilter.filterBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Criteria-based queries
 */
@SpringBootTest
public class CriteriaQueryTest {

    @Autowired
    private DynamicQueryService queryService;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private StaffRepo staffRepo;

    @Test
    void findByCriteria() {
        UniversityFactory.fillUniversity(universityService);

        Department humanities = departmentRepo.findOne(Example.of(new Department("Humanities", null),
                ExampleMatcher.matching().withIgnoreCase())).orElse(null);
        Staff professorBlack = staffRepo.findAll(Example.of(new Staff(new Person(null, "Black")),
                ExampleMatcher.matching().withIgnoreCase())).stream().findFirst().orElse(null);

        System.out.println('\n' + "*** All Humanities Courses");
        queryAndVerify(filterBy().department(humanities));

        System.out.println('\n' + "*** 4 credit courses");
        queryAndVerify(filterBy().credits(4));

        System.out.println('\n' + "*** Courses taught by Professor Black");
        queryAndVerify(filterBy().instructor(professorBlack));

        System.out.println('\n' + "*** Courses In Humanties, taught by Professor Black, 4 credits");
        queryAndVerify(filterBy()
                .department(humanities)
                .credits(4)
                .instructor(professorBlack));
    }


    private void queryAndVerify(CourseFilter filter) {
        queryService.filterByExample(filter)
                .forEach(course -> {
                    filter.getInstructor().ifPresent(i -> assertEquals(i, course.getInstructor()));
                    filter.getCredits().ifPresent(c -> assertEquals(c, course.getCredits()));
                    filter.getDepartment().ifPresent(prof -> assertEquals(prof, course.getDepartment()));
                    System.out.println(course);
                });
    }
}
