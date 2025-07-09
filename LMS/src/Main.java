import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book{
    String title="";
    String author="";
    String genre="";
    boolean availability=false;
}

class User{
    String name="";
    String password="";
    int contactInfo;
    List<String> borrowedBooks = new ArrayList<>();
}

class Admin {
    public void addBook(ArrayList<Book> books, Book book) {
        books.add(book);
        System.out.println("Book is Successfully added");
    }

    public void removeBook(ArrayList<Book> books, String titleToRemove) {
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).title.equalsIgnoreCase(titleToRemove)){
                books.remove(i);
                found=true;
                System.out.println("Book removed "+titleToRemove);
                break;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }

}

public class Main {
    public static void userMenu(){
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. View All Books");
        System.out.println("6. View Borrowed Books");
        System.out.println("7. Exit");
    }
    public static void adminMenu(){
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. View All Books");
        System.out.println("7. Exit");
    }

    public static User loginUser(List<User> users, Scanner sc) {
        System.out.println("Enter User name");
        String userName = sc.nextLine();

        System.out.println("Enter User Password");
        String userPass = sc.nextLine();

        System.out.println("Enter Contact Info");
        int contactInfo = sc.nextInt();
        sc.nextLine(); // clear buffer

        for (User user : users) {
            if (user.name.equals(userName) &&
                    user.password.equals(userPass) &&
                    user.contactInfo == contactInfo) {
                System.out.println("Logged in Successfully");
                return user;
            }
        }

        System.out.println("Invalid login credentials.");
        return null;
    }


    public static void main(String[] args) {
        String ADMIN_USERNAME = "admin";
        String ADMIN_PASSWORD = "password123";
        boolean isAdminLoggedIn = false;


        System.out.println("Library Management System in Java");
        ArrayList<Book> books = new ArrayList<>();
        // Preloaded books
        Book book1 = new Book();
        book1.title = "The Great Gatsby";
        book1.author = "F. Scott Fitzgerald";
        book1.genre = "Classic";
        book1.availability = true;

        Book book2 = new Book();
        book2.title = "1984";
        book2.author = "George Orwell";
        book2.genre = "Dystopian";
        book2.availability = true;

        Book book3 = new Book();
        book3.title = "To Kill a Mockingbird";
        book3.author = "Harper Lee";
        book3.genre = "Classic";
        book3.availability = true;

        Book book4 = new Book();
        book4.title = "Pride and Prejudice";
        book4.author = "Jane Austen";
        book4.genre = "Romance";
        book4.availability = true;

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);

        ArrayList<User> users = new ArrayList<>();
        User defaultUser = new User();
        defaultUser.name = "user1";
        defaultUser.password = "pass1";
        defaultUser.contactInfo = 12345;
        users.add(defaultUser);

        Scanner sc=new Scanner(System.in);
        int userOpt;
        boolean isUserLoggedIn = false;
        User currentUser = null;
        String adminName;
        String adminPass;

        System.out.println("You are logging in as:");
        System.out.println("1- ADMIN");
        System.out.println("2- USER");
        int logInOpt= sc.nextInt();
        sc.nextLine(); // consume leftover newline

        if(logInOpt==1){

            System.out.println("Enter Name");
            adminName=sc.nextLine();
            System.out.println("Enter password");
            adminPass=sc.nextLine();

            if(adminName.equalsIgnoreCase(ADMIN_USERNAME)&& adminPass.equalsIgnoreCase(ADMIN_PASSWORD)){
                System.out.println("Admin Logged IN");
                isAdminLoggedIn = true;
            } else {
                System.out.println("Invalid admin credentials. Continuing as user...");
            }
        }else if(logInOpt==2){

            System.out.println("1. Register as a User");
            System.out.println("2. Login as a User");
            userOpt=sc.nextInt();
            sc.nextLine();

            if(userOpt==1){

                User newuser = new User();
                System.out.println("Enter name");
                newuser.name = sc.nextLine();
                System.out.println("Enter Password");
                newuser.password = sc.nextLine();
                System.out.println("Enter Contact Info");
                newuser.contactInfo = sc.nextInt();
                sc.nextLine();
                users.add(newuser);
                System.out.println("User registered Successfully");

                System.out.println("NOW LOG IN PLEASE");
                currentUser = loginUser(users, sc);
                if (currentUser != null) {
                    isUserLoggedIn = true;
                }

            }if(userOpt==2){
                currentUser = loginUser(users, sc);
                if (currentUser != null) {
                    isUserLoggedIn = true;
                }
            }if (userOpt != 1 && userOpt != 2) {
                System.out.println("Invalid option. Exiting...");
                return;
            }
        }
        Admin admin = new Admin();

        if (isAdminLoggedIn || isUserLoggedIn) {
            while (true) {
                if (isAdminLoggedIn) {
                    adminMenu();
                } else {
                    userMenu();
                }

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        if (!isAdminLoggedIn) {
                            System.out.println("Access denied. Only admin can add books.");
                            break;
                        }
                        System.out.println("To Add book");
                        Book newbook = new Book();
                        System.out.println("Enter Title");
                        newbook.title = sc.nextLine();
                        System.out.println("Enter author");
                        newbook.author = sc.nextLine();
                        System.out.println("Enter genre");
                        newbook.genre = sc.nextLine();
                        newbook.availability = true;
                        admin.addBook(books, newbook);
                        break;
                    case 2:
                        if (!isAdminLoggedIn) {
                            System.out.println("Access denied. Only admin can remove books.");
                            break;
                        }
                        System.out.println("To Remove Book Enter Book Title");
                        String titleToRemove = sc.nextLine();
                        admin.removeBook(books, titleToRemove);
                        break;
                    case 3:
                        if (!isUserLoggedIn) {
                            System.out.println("You must be logged in as a user to borrow books.");
                            break;
                        }

                        System.out.println("To Borrow Book");
                        System.out.println("Enter the title of the book to borrow");

                        String titleToBorrow = sc.nextLine();
                        boolean borrowed = false;
                        for (int i = 0; i < books.size(); i++) {
                            if (books.get(i).title.equalsIgnoreCase(titleToBorrow)) {
                                if (books.get(i).availability) {
                                    books.get(i).availability = false;
                                    currentUser.borrowedBooks.add(books.get(i).title);
                                    System.out.println("Book Borrowed Successfully " + books.get(i).title);

                                } else {
                                    System.out.println("Book is currently not available.");
                                }
                                borrowed = true;
                                break;
                            }

                        }
                        if (!borrowed) {
                            System.out.println("Book not found.");
                        }
                        break;
                    case 4:
                        if (!isUserLoggedIn) {
                            System.out.println("You must be logged in as a user to return books.");
                            break;
                        }

                        System.out.println("To Return a Book");
                        System.out.println("Enter title");
                        String returnBook = sc.nextLine();
                        boolean returned = false;

                        for (int i = 0; i < books.size(); i++) {
                            if (books.get(i).title.equalsIgnoreCase(returnBook)) {
                                returned = true;

                                if (currentUser.borrowedBooks.contains(books.get(i).title)) {
                                    if (!books.get(i).availability) {
                                        books.get(i).availability = true;
                                        currentUser.borrowedBooks.remove(books.get(i).title);
                                        System.out.println("Book Returned Successfully.");

                                    } else {
                                        System.out.println("This book is already marked as available.");
                                    }
                                } else {
                                    System.out.println("You did not borrow this book.");
                                }
                                break;
                            }
                        }

                        if (!returned) {
                            System.out.println("Book not found.");
                        }
                        break;

                    case 5:
                        System.out.println("All Books");
                        System.out.println("-----------------------");
                        for (int i = 0; i < books.size(); i++) {
                            System.out.println("Title " + books.get(i).title);
                            System.out.println("Author " + books.get(i).author);
                            System.out.println("Genre " + books.get(i).genre);
                            System.out.println("Availability: " + (books.get(i).availability ? "Available" : "Not Available"));
                            System.out.println("-----------------------");

                        }
                        break;

                    case 6:
                        if (!isUserLoggedIn) {
                            System.out.println("You must be logged in as a user.");
                            break;
                        }
                        System.out.println("Your Borrowed Books:");
                        if (currentUser.borrowedBooks.isEmpty()) {
                            System.out.println("You have not borrowed any books.");
                        }else {
                            for (int i = 0; i < currentUser.borrowedBooks.size(); i++) {
                                System.out.println("- " + currentUser.borrowedBooks.get(i));
                            }
                        }
                        break;

                    case 7:
                        System.out.println("Exiting the system. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

            }
        }else {
            System.out.println("Login failed. Exiting program.");
            System.out.println("hehehe");
        }
    }
}