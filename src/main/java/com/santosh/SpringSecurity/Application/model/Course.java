package com.santosh.SpringSecurity.Application.model;

import jakarta.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    private int totalClasses;
    private int totalCredits;

    // Constructor
    public Course() {
    }

    public Course(String courseName, int totalCredits) {

        this.courseName = courseName;
        this.totalCredits = totalCredits;
        this.totalClasses = 12 * totalCredits;
    }

    // Getters and Setter

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
