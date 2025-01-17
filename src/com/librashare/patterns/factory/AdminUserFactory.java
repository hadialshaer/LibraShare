package com.librashare.patterns.factory;

import com.librashare.models.User;
import com.librashare.patterns.builder.AdminUserBuilder;
import com.librashare.patterns.builder.UserBuilder;

public class AdminUserFactory extends UserFactory{
    @Override
    public User createUser (String firstName, String lastName, String email, String personalNumber, String birthdate, String gender) {
        UserBuilder adminBuilder = new AdminUserBuilder();
        return adminBuilder
            .setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setPersonalNumber(personalNumber)
            .setBirthdate(birthdate)
            .setGender(gender).build();
    }
}
