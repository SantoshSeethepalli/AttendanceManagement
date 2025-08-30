package com.santosh.SpringSecurity.Application.controller;

import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course/")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/getAllAvailableCourses")
    public ResponseEntity<List<Course>> getAllAvailableCourses() {

        List<Course> listOfAvailableCourses = courseService.getAllCourses();

        return ResponseEntity.ok(listOfAvailableCourses);
    }
}
