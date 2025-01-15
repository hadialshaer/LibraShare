package com.booklink.patterns.factory;

import com.booklink.models.User;
import com.booklink.patterns.builder.AdminUserBuilder;
import com.booklink.patterns.builder.UserBuilder;

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
