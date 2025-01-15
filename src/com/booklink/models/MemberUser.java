package com.booklink.models;

public class MemberUser extends User {

    // Constructor called by the builder
    public MemberUser() {
        super(UserRole.MEMBER);
    }

    @Override
    public void showPermissions() {
        System.out.println("Member Permissions: Can view, borrow, and return books.");
    }
}
