package com.aoher.dto;

import java.io.Serializable;

public class StudentDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public StudentDTO(){}

    public StudentDTO(int sID){
        this.id = sID;
    }

    public StudentDTO(int sID, String sFirstName, String sLastName, String sEmail){
        this.id = sID;
        this.firstName = sFirstName;
        this.lastName = sLastName;
        this.email = sEmail;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}