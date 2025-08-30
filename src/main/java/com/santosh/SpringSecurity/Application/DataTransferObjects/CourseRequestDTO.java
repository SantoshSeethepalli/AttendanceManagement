package com.santosh.SpringSecurity.Application.DataTransferObjects;

public class CourseRequestDTO {
    private String courseName;
    private int totalCredits;

    public CourseRequestDTO() {

    }

    public CourseRequestDTO(String courseName, int totalCredits) {
        this.courseName = courseName;
        this.totalCredits = totalCredits;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }
}
