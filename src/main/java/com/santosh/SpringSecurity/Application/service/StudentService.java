package com.santosh.SpringSecurity.Application.service;

import com.santosh.SpringSecurity.Application.DataTransferObjects.CreateStudentDTO;
import com.santosh.SpringSecurity.Application.DataTransferObjects.StudentResponseDTO;
import com.santosh.SpringSecurity.Application.model.Course;
import com.santosh.SpringSecurity.Application.model.Role;
import com.santosh.SpringSecurity.Application.model.Student;
import com.santosh.SpringSecurity.Application.repo.StudentRepository;
import com.santosh.SpringSecurity.Authentication.model.User;
import com.santosh.SpringSecurity.Authentication.repo.UserRepo;
import com.santosh.SpringSecurity.Authentication.service.UsersAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UsersAuthService userService;

    public StudentResponseDTO saveUser(CreateStudentDTO studentDTO) {

        if(studentDTO.getUsername().trim().isEmpty() || studentDTO.getPassword().trim().isEmpty()) {
            return null;
        }

        User user = new User();
        user.setUsername(studentDTO.getUsername());
        user.setPassword(studentDTO.getPassword());
        user.setRole(Role.STUDENT);
        userService.save(user);

        Student student = new Student();
        student.setUser(user);
        studentRepository.save(student);

        StudentResponseDTO dto =
                new StudentResponseDTO(student.getId(), user.getUsername(), user.getRole().name());

        System.out.println("Returning DTO: " + dto.getUsername() + " id=" + dto.getId());
        return dto;
    }

    public Optional<Student> findById(Long id) {

        return studentRepository.findById(id);
    }
}
