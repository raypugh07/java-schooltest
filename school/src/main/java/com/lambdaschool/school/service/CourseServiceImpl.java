package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.view.CountStudentsInCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "courseService")
public class CourseServiceImpl implements CourseService
{
    @Autowired
    private CourseRepository courserepos;

    @Override
    public ArrayList<Course> findAll()
    {
        ArrayList<Course> list = new ArrayList<>();
        courserepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Course findCourseById(long courseid) {
        return courserepos.findById(courseid).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public ArrayList<CountStudentsInCourses> getCountStudentsInCourse()
    {
        return courserepos.getCountStudentsInCourse();
    }

    @Override
    public Course save(Course todo) {
        return courserepos.save(todo);
    }

    @Override
    public Course add(Course course) {
        return courserepos.save(course);
    }

    @Transactional
    @Override
    public void delete(long courseid) throws EntityNotFoundException
    {
        if (courserepos.findById(courseid).isPresent())
        {
            courserepos.deleteCourseFromStudcourses(courseid);
            courserepos.deleteById(courseid);
        } else
        {
            throw new EntityNotFoundException(Long.toString(courseid));
        }
    }


}
