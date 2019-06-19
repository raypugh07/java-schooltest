package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import com.lambdaschool.school.service.InstructorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith( SpringRunner.class )
@WebMvcTest(value = CourseController.class,secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    InstructorService instructorService;

    @MockBean
    CourseService courseService;



    private List<Course> courseList;

    @Before
    public void setUp() throws Exception {

       courseList=new ArrayList<>();
        Instructor instructor1=instructorService.findInstructorById(3);
        String course1Name="Php";
        Course c1=new Course(course1Name,instructor1);
        courseList.add(c1);


        Instructor instructor2=instructorService.findInstructorById(1);
        String course2Name="Ruby";
        Course c2=new Course(course2Name,instructor2);
        courseList.add(c2);




    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void listAllCourses() throws Exception{

        String apiUrl="/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn((ArrayList<Course>) courseList);

        RequestBuilder tb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(tb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expected = mapper.writeValueAsString(courseList);

        assertEquals(expected, tr);



    }

    @Test
    public void getCountStudentsInCourses() {
    }

    @Test
    public void deleteCourseById() {
    }
}