package com.santosh.SpringSecurity.Application.repo;

import com.santosh.SpringSecurity.Application.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(long userId);
    Optional<Student> findByUserUsername(String username);
}

