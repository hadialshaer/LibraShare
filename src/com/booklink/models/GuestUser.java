package com.booklink.models;

public class GuestUser extends User {

    // Constructor called by the builder
    public GuestUser() {
        super(UserRole.GUEST);
    }

    @Override
    public void showPermissions() {
        System.out.println("Guest Permissions: Can only view books.");
    }
}
