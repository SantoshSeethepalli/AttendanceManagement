package com.santosh.SpringSecurity.Application.repo;

import com.santosh.SpringSecurity.Application.model.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findByStudentId(Long studentId);

    Optional<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}

