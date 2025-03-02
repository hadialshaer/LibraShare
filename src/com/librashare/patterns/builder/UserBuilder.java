package com.librashare.patterns.builder;

import com.librashare.models.User;

public interface UserBuilder {
    UserBuilder setFirstName(String firstName);
    UserBuilder setLastName(String lastName);
    UserBuilder setEmail(String email);
    UserBuilder setPersonalNumber(String password);
    UserBuilder setBirthdate(String birthDate);
    UserBuilder setGender(String gender);

    // Build the final User object
    User build();
}
