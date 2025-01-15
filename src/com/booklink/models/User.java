package com.booklink.models;

public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private String personalNumber;
    private String birthdate;
    private String gender;

    private UserRole role;

    // Protected constructor so only subclasses can invoke it
    protected User(UserRole role) {
        this.role = role;
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

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // Getters for reading the data
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public UserRole getRole() {
        return role;
    }

    // Each subclass defines how permissions are handled
    public abstract void showPermissions();
}