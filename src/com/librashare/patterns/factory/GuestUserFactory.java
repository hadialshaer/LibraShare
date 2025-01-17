package com.librashare.patterns.factory;

import com.librashare.models.User;
import com.librashare.patterns.builder.GuestUserBuilder;
import com.librashare.patterns.builder.UserBuilder;

public class GuestUserFactory extends UserFactory {

    @Override
    public User createUser(String firstName, String lastName, String email, String personalNumber, String birthdate, String gender) {
        UserBuilder guestBuilder = new GuestUserBuilder();
        return guestBuilder
            .setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setPersonalNumber(personalNumber)
            .setBirthdate(birthdate)
            .setGender(gender).build();
    }
}
