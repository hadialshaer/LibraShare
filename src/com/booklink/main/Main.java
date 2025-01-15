package com.booklink.main;
import com.booklink.models.User;
import com.booklink.patterns.factory.AdminUserFactory;
import com.booklink.patterns.factory.GuestUserFactory;
import com.booklink.patterns.factory.MemberUserFactory;
import com.booklink.patterns.factory.UserFactory;
import com.booklink.patterns.singleton.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        // verify both references point to the same instance
        System.out.println("Are both references pointing to the same instance? " + (db1 == db2));

        db1.executeQuery("SELECT * FROM books");
        db2.executeQuery("SELECT * FROM authors");

        // Create a Guest user
        UserFactory guestFactory = new GuestUserFactory();
        User guest = guestFactory.createUser(
                "Mohammad",
                "Ammar",
                "MohammadAmmar@gmail.com",
                "12345678",
                "01-01-2000",
                "Male"
        );
        guest.showPermissions();

        // Create a Member user
        UserFactory memberFactory = new MemberUserFactory();
        User member = memberFactory.createUser(
                "Hussein",
                "Ammar",
                "HusseinAmmar@gmail.com",
                "12312312",
                "02-02-2000",
                "Male"
        );
        member.showPermissions();

        // Create an Admin user
        UserFactory adminFactory = new AdminUserFactory();
        User admin = adminFactory.createUser(
                "Hadi",
                "Alshaer",
                "HadiAlshaer@gmail.com",
                "99999999",
                "03-03-2000",
                "Male"
        );
        admin.showPermissions();
    }
}