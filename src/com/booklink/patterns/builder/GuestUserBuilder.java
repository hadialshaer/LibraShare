package com.booklink.patterns.builder;

import com.booklink.models.GuestUser;
import com.booklink.models.User;

public class GuestUserBuilder implements UserBuilder {
    private final GuestUser guestUser;

    public GuestUserBuilder() {
        this.guestUser = new GuestUser();
    }

    @Override
    public UserBuilder setFirstName(String firstName) {
        guestUser.setFirstName(firstName);
        return this;
    }
    @Override
    public UserBuilder setLastName(String lastname) {
        guestUser.setLastName(lastname);
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        guestUser.setEmail(email);
        return this;
    }

    @Override
    public UserBuilder setPersonalNumber(String personalNumber) {
        guestUser.setPersonalNumber(personalNumber);
        return this;
    }

    @Override
    public UserBuilder setBirthdate(String birthdate) {
        guestUser.setBirthdate(birthdate);
        return this;
    }

    @Override
    public UserBuilder setGender(String gender) {
        guestUser.setGender(gender);
        return this;
    }

    @Override
    public User build() {
        return guestUser;
    }
}
