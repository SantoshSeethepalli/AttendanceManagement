package com.santosh.SpringSecurity.Application.service;

import com.santosh.SpringSecurity.Application.DataTransferObjects.CourseRequestDTO;
import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course saveCourse(CourseRequestDTO course) {

        Course newCourse = new Course(course.getCourseName(), course.getTotalCredits());

        return courseRepository.save(newCourse);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
