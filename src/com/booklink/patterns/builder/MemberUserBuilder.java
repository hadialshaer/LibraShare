package com.booklink.patterns.builder;

import com.booklink.models.MemberUser;
import com.booklink.models.User;

public class MemberUserBuilder implements UserBuilder {
    private final MemberUser memberUser;

    public MemberUserBuilder() {
        memberUser = new MemberUser();
    }

    @Override
    public UserBuilder setFirstName(String firstname) {
        memberUser.setFirstName(firstname);
        return this;
    }

    @Override
    public UserBuilder setLastName(String lastname) {
        memberUser.setLastName(lastname);
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        memberUser.setEmail(email);
        return this;
    }

    @Override
    public UserBuilder setPersonalNumber(String personalNumber) {
        memberUser.setPersonalNumber(personalNumber);
        return this;
    }

    @Override
    public UserBuilder setBirthdate(String birthdate) {
        memberUser.setBirthdate(birthdate);
        return this;
    }

    @Override
    public UserBuilder setGender(String gender) {
        memberUser.setGender(gender);
        return this;
    }

    @Override
    public User build() {
        return memberUser;
    }
}
