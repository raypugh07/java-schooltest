package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.InstructorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Before;


import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    InstructorService instructorService;

    @Before
   public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {

        assertEquals(6,courseService.findAll().size());

    }

    @Test
    public void findCourseById() {

        assertEquals("Data Science",courseService.findCourseById(1).getCoursename());
    }

    @Test
    public void getCountStudentsInCourse() {
    }

    @Test
    public void delete() {

        courseService.delete(2);
        assertEquals(5,courseService.findAll().size());
    }

    @Test//(expected = EntityNotFoundException.class)
    public void deleteNotFound() {

        courseService.delete(1000);
        assertEquals(5,courseService.findAll().size());
    }

    @Test
    public void addCourse(){

       // instructorService.findInstructorById(3);
        String course7Name="Python";
        Instructor instructor1=instructorService.findInstructorById(3);
        Course c7=new Course(course7Name,instructor1);
        Course addCourse=courseService.save(c7);

        assertNotNull(addCourse);
        Course foundCourse=courseService.findCourseById(addCourse.getCourseid());
        assertEquals(addCourse.getCoursename(),foundCourse.getCoursename());


    }



}