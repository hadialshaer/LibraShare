package com.librashare.patterns.factory;

import com.librashare.models.User;
import com.librashare.patterns.builder.MemberUserBuilder;
import com.librashare.patterns.builder.UserBuilder;

public class MemberUserFactory extends UserFactory{
    public User createUser(String firstName, String lastName, String email, String personalNumber, String birthdate, String gender) {
        UserBuilder memberBuilder = new MemberUserBuilder();
        return memberBuilder
            .setFirstName(firstName)
            .setLastName(lastName)
            .setEmail(email)
            .setPersonalNumber(personalNumber)
            .setBirthdate(birthdate)
            .setGender(gender).build();
    }
}
