package com.santosh.SpringSecurity.Application.controller;

import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.model.Student;
import com.santosh.SpringSecurity.Application.model.StudentCourse;
import com.santosh.SpringSecurity.Application.service.CourseService;
import com.santosh.SpringSecurity.Application.service.StudentCourseService;
import com.santosh.SpringSecurity.Authentication.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/enroll/{userId}/{courseId}")
    public ResponseEntity<StudentCourse> enroll(@PathVariable Long userId, @PathVariable Long courseId) {

        StudentCourse enrolledCourse = studentCourseService.enroll(userId, courseId);

        return ResponseEntity.ok(enrolledCourse);
    }

    @PostMapping("/increaseAbsentCount/{userId}/{courseId}")
    public ResponseEntity<StudentCourse> increaseAbsentCount(@PathVariable Long userId, @PathVariable Long courseId) {

        StudentCourse updatedEnrollment = studentCourseService.incrementAbsentCount(userId, courseId);

        return ResponseEntity.ok(updatedEnrollment);
    }

    @GetMapping("/getEnrolledCourses")
    public ResponseEntity<List<StudentCourse>> getMyCourses(Authentication authentication) {

        List<StudentCourse> courses = studentCourseService.getEnrolledCourses(authentication);

        return ResponseEntity.ok(courses);
    }
}
