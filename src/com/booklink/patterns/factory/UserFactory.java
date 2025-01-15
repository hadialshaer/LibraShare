package com.booklink.patterns.factory;

import com.booklink.models.User;

public abstract class UserFactory {
    public abstract User createUser(
        String firstName,
        String lastName,
        String email,
        String personalNumber,
        String birthdate,
        String gender
    );
}
