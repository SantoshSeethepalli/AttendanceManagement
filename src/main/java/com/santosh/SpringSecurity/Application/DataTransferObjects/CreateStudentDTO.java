package com.santosh.SpringSecurity.Application.DataTransferObjects;

public class CreateStudentDTO {

    private String username;
    private String password;

    public CreateStudentDTO() {
    }

    public CreateStudentDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
