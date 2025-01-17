package com.librashare.models;

public class AdminUser extends User {

    // Constructor called by the builder
    public AdminUser() {
        super(UserRole.ADMIN);
    }

    @Override
    public void showPermissions() {
        System.out.println("Admin Permissions: Can view, borrow, return, add, and remove books.");
    }
}
