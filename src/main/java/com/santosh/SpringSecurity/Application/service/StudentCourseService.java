package com.santosh.SpringSecurity.Application.service;

import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.model.Student;
import com.santosh.SpringSecurity.Application.model.StudentCourse;
import com.santosh.SpringSecurity.Application.repo.CourseRepository;
import com.santosh.SpringSecurity.Application.repo.StudentCourseRepository;
import com.santosh.SpringSecurity.Application.repo.StudentRepository;
import com.santosh.SpringSecurity.Authentication.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public StudentCourse enroll(Long userId, Long courseId) {

        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Long studentId = student.getId();

        Optional<StudentCourse> possibleExistingStudent = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);

        if (possibleExistingStudent.isPresent()) {
            throw new RuntimeException("Student already enrolled in this course");
        }

        StudentCourse sc = new StudentCourse();
        sc.setStudent(student);
        sc.setCourse(course);
        sc.setAbsentCount(0);

        return studentCourseRepository.save(sc);
    }


    public StudentCourse incrementAbsentCount(Long userId, Long courseId) {

        Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No Student found this student ID")
                );

        long studentId = student.getId();

        StudentCourse sc = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId).orElseThrow(() ->
                new RuntimeException("Enrollment not found")
        );

        sc.setAbsentCount(sc.getAbsentCount() + 1);

        return studentCourseRepository.save(sc);
    }

    public List<StudentCourse> getStudentCourses(Long studentId) {

        return studentCourseRepository.findByStudentId(studentId);
    }

    public List<StudentCourse> getEnrolledCourses(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        long userId = userPrincipal.getId();

        Student student = studentRepository.findByUserId((long) userId)
                .orElseThrow(() -> new RuntimeException("Student not found for user"));

        Long studentId = student.getId();

        List<StudentCourse> enrollments = studentCourseRepository.findByStudentId(studentId);

        return enrollments;
    }
}