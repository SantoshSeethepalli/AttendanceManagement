package com.santosh.SpringSecurity.Application.repo;

import com.santosh.SpringSecurity.Application.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

