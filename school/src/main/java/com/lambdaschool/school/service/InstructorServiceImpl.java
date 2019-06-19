package com.lambdaschool.school.service;

import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "instructorService")
public class InstructorServiceImpl implements InstructorService
{
    @Autowired
    private InstructorRepository instructrepos;

    @Override
    public Instructor findInstructorById(long instructid) {
        return instructrepos.findById(instructid).orElseThrow(EntityNotFoundException::new);
    }

}
