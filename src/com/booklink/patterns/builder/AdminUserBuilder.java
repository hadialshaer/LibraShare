package com.booklink.patterns.builder;

import com.booklink.models.AdminUser;
import com.booklink.models.User;

public class AdminUserBuilder implements UserBuilder {
    private final AdminUser adminUser;

    public AdminUserBuilder() {
        adminUser = new AdminUser();
    }

    @Override
    public UserBuilder setFirstName(String firstName) {
        adminUser.setFirstName(firstName);
        return this;
    }

    @Override
    public UserBuilder setLastName(String lastName) {
        adminUser.setLastName(lastName);
        return this;
    }

    public UserBuilder setEmail(String email) {
        adminUser.setEmail(email);
        return this;
    }

    public UserBuilder setPersonalNumber(String personalNumber) {
        adminUser.setPersonalNumber(personalNumber);
        return this;
    }

    public UserBuilder setBirthdate(String birthdate) {
        adminUser.setBirthdate(birthdate);
        return this;
    }

    public UserBuilder setGender(String gender) {
        adminUser.setGender(gender);
        return this;
    }

    @Override
    public User build() {
        return adminUser;
    }


}
