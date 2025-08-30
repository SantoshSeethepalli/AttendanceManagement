package com.santosh.SpringSecurity.Application.controller;

import com.santosh.SpringSecurity.Application.DataTransferObjects.CourseRequestDTO;
import com.santosh.SpringSecurity.Application.DataTransferObjects.CreateStudentDTO;
import com.santosh.SpringSecurity.Application.DataTransferObjects.StudentResponseDTO;
import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.model.Student;
import com.santosh.SpringSecurity.Application.service.CourseService;
import com.santosh.SpringSecurity.Application.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/addStudent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponseDTO> addStudent(@RequestBody CreateStudentDTO studentDTO) {

        StudentResponseDTO savedStudent = studentService.saveUser(studentDTO);

        if(savedStudent == null) {

            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(savedStudent);
    }

    @PostMapping(value = "/addCourse", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Course> addCourse(@RequestBody CourseRequestDTO courseRequest) {

        Course savedCourse = courseService.saveCourse(courseRequest);

        if (savedCourse == null) {

            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(savedCourse);
    }


}

