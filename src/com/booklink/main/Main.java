package com.booklink.main;
import com.booklink.models.Book;
import com.booklink.models.User;
import com.booklink.patterns.factory.AdminUserFactory;
import com.booklink.patterns.factory.GuestUserFactory;
import com.booklink.patterns.factory.MemberUserFactory;
import com.booklink.patterns.factory.UserFactory;
import com.booklink.patterns.proxy.BookService;
import com.booklink.patterns.proxy.BookServiceImp;
import com.booklink.patterns.proxy.BookServiceProxy;
import com.booklink.patterns.singleton.DatabaseConnection;

public class Main {
    public static void main(String[] args) {

        // 1. Test Singleton
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();

        // verify both references point to the same instance
        System.out.println("Are both references pointing to the same instance? " + (db1 == db2));

        db1.executeQuery("SELECT * FROM books");
        db2.executeQuery("SELECT * FROM authors");

        // 2. Test Factory + Builder: Create different users
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

        // 3. Test Proxy: Role-based access to BookService
        //    a) Create the real service
        BookService realService = new BookServiceImp();

        //    b) Wrap the service with a proxy for the guest user
        BookService guestProxy = new BookServiceProxy(realService, guest);

        //    c) Try some operations
        System.out.println("\n--- Guest tries to add a book ---");
        guestProxy.addBook(new Book(2, "Effective Java", "Joshua Bloch", "Addison-Wesley", "Science", "978-0134685991"));

        System.out.println("\n--- Guest tries to borrow a book ---");
        guestProxy.borrowBook(2);

        //    d) Wrap the service with a proxy for the member user
        BookService memberProxy = new BookServiceProxy(realService, member);

        System.out.println("\n--- Member tries to add a book ---");
        memberProxy.addBook(new Book(3, "Java Concurrency in Practice", "Brian Goetz", "Addison-Wesley", "Science", "978-0321349606"));

        System.out.println("\n--- Member borrows a book ---");
        memberProxy.borrowBook(3);

        //    e) Wrap the service with a proxy for the admin user
        BookService adminProxy = new BookServiceProxy(realService, admin);

        System.out.println("\n--- Admin tries to add a book ---");
        adminProxy.addBook(new Book(4, "Head First Design Patterns", "Eric Freeman", "O'Reilly Media", "Science", "978-0596007126"));

        System.out.println("\n--- Admin borrows a book ---");
        adminProxy.borrowBook(4);

        System.out.println("\n--- Admin removes a book ---");
        adminProxy.removeBook(2);
    }

}