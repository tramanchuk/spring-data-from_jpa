package com.example.business;

import com.example.dao.CourseDao;
import com.example.dao.DepartmentDao;
import com.example.dao.StaffDao;
import com.example.dao.StudentDao;
import com.example.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Business Tier class for the Univeirsity Library
 */
@Repository
public class UniversityService {

    private DepartmentDao departmentDao;

    private StaffDao staffDao;

    private StudentDao studentDao;

    private CourseDao courseDao;

    public UniversityService(CourseDao courseDao, DepartmentDao departmentDao, StaffDao staffDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.departmentDao = departmentDao;
        this.staffDao = staffDao;
        this.studentDao = studentDao;
    }

    public Student createStudent(String firstName, String lastName, boolean fullTime, int age) {
        return studentDao.save(new Student(new Person(firstName, lastName), fullTime, age));
    }

    public Staff createFaculty(String firstName, String lastName) {
        return staffDao.save(new Staff(new Person(firstName, lastName)));
    }

    public Department createDepartment(String deptname, Staff deptChair) {
        return departmentDao.save(new Department(deptname, deptChair));
    }

    public Course createCourse(String name, int credits, Staff professor, Department department) {
        return courseDao.save(new Course(name, credits, professor, department));
    }

    public Course createCourse(String name, int credits, Staff professor, Department department, Course... prereqs) {
        Course c = new Course(name, credits, professor, department);
        for (Course p : prereqs) {
            c.addPrerequisite(p);
        }
        return courseDao.save(c);
    }

    public List<Course> findAllCourses() {
        return courseDao.findAll();
    }

    public List<Staff> findAllStaff() {
        return staffDao.findAll();
    }

    public List<Department> findAllDepartments() {
        return departmentDao.findAll();
    }

    public List<Student> findAllStudents() {
        return studentDao.findAll();
    }

    public void deleteAll() {
        try {
            studentDao.deleteAll();
            courseDao.deleteAll();
            departmentDao.deleteAll();
            staffDao.deleteAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
