import java.util.*;

//this class represents the actions that can be done by the staff members
public class Actions {
    Scanner sc= new Scanner(System.in);
    Employee currentUser=null;  //this represents the current user logged in
    ArrayList<String> genres=new ArrayList<>(
            Arrays.asList("Fiction","Non-fiction","Poetry","Drama","History","Classic literature","Science","Comics","CookBooks","Art","Biography")
    );
    public void start() throws UnavailableBookException, NoSuchElementException {
        System.out.println("----------------------Welcome----------------------");
        System.out.println("1- Login");
        System.out.println("2- Signup");
        System.out.println("0- Exit");
        System.out.println("--------------------------------------------------");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                login();
                break;
            case 2:
                signup();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
                start();
        }
    }
    public void signup() throws UnavailableBookException, NoSuchElementException {
        if(currentUser!=null)
        {
            System.out.println("You are already logged in!");
            return;
        }
        System.out.println("Please enter your name: ");
        String name=sc.nextLine();
        name=sc.nextLine();
        System.out.println("Please enter your email: ");
        String email=sc.nextLine();
        System.out.println("Please enter your address: ");
        String address=sc.nextLine();
        System.out.println("Please enter your username: ");
        String username=sc.nextLine();
        while(Employee.UsernameExists(username))
        {
            System.out.println("This username is already taken. Please enter another username: ");
            username=sc.nextLine();
        }
        System.out.println("Please enter your password: ");
        String password=sc.nextLine();
        while(password.length()<8)
        {
            System.out.println("Your password must be at least 8 characters long. Please enter another password: ");
            password=sc.nextLine();
        }
        currentUser=new Employee(name,username,password,email,address);
        System.out.println("You have successfully signed up!");
        System.out.println("Your badge ID is: "+currentUser.getBadgeID());
        System.out.println("Welcome!");
        System.out.println("---------------------------------------------------------------------------------------------");
        menu();
    }
    public void login() throws UnavailableBookException, NoSuchElementException {
        int choice;
        if(currentUser!=null)
        {
            System.out.println("You are already logged in!");
            return;
        }
        System.out.println("Please enter your username: ");
        String username=sc.nextLine();
        username=sc.nextLine();
        System.out.println("Please enter your password: ");
        String password=sc.nextLine();
        currentUser=Employee.authenticateEmployee(username,password);
        if(currentUser==null) {
            System.out.println("1-Retry\n0-Return to the Main menu");
            choice = sc.nextInt();
            while (choice != 0 && choice != 1) {
                System.out.println("Invalid choice, please try again");
                choice = sc.nextInt();
            }
            if (choice == 1) {
                login();
            } else {
                start();
            }
        }
        else{
            System.out.println("You have successfully logged in!");
            System.out.println("Welcome "+currentUser.getName()+"!");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
    }
    public void menu() throws  UnavailableBookException, NoSuchElementException {
        int choice;
        System.out.println("""
                Please choose an action:
                1- View Staff
                2- View all books
                3- View available books
                4- Filter by genre
                5- View currently borrowed books
                6- View borrowing history
                7- Add a book to the library
                8- Remove a book from the library
                9- Register a new member
                10- Remove a member
                11- View all members
                12- Register a book borrow
                13- Register a book return
                14- View a member details
                15- View a member borrowing history
                16- View a member currently borrowed books
                17- View general Analytics
                0- Logout
                """);
        choice=sc.nextInt();
        switch(choice)
        {
            case 1: //View Staff
                viewStaff();
                menu();
                break;
            case 2: //View all books
                viewAllBooks();
                menu();
                break;
            case 3: //View available books
                viewAvailableBooks();
                menu();
                break;
            case 4: //Filter by genre
                filterByGenre();
                menu();
                break;
            case 5: //View currently borrowed books
                viewCurrentlyBorrowedBooks();
                menu();
                break;
            case 6: //View borrowing history
                System.out.println("-------------------------------------------Borrowing History-------------------------------------------------");
                Borrow.printBorrows();
                System.out.println("---------------------------------------------------------------------------------------------");
                menu();
                break;
            case 7: //Add a book to the library
                addBookToLibrary();
                menu();
                break;
            case 8: //Remove a book from the library
                removeBookFromLibrary();
                menu();
                break;
            case 9: //Register a new member
                registerMember();
                menu();
                break;
            case 10: //Remove a member
                removeMember();
                menu();
                break;
            case 11:   //View all members
                viewAllMembers();
                menu();
                break;
            case 12: //Register a book borrow
                registerBookBorrow();
                menu();
                break;
            case 13: //Register a book return
                registerBookReturn();
                menu();
                break;
            case 14: //View a member details
                viewMemberDetails();
                menu();
                break;
            case 15: //View a member borrowing history
                viewMemberBorrowingHistory();
                menu();
                break;
            case 16: //View a member currently borrowed books
                viewMemberCurrentlyBorrowedBooks();
                menu();
                break;
            case 17: //View general Analytics
                ShowAnalytics();
                menu();
                break;
            case 0: //Logout
                currentUser=null;
                start();
                break;
            default:
                System.out.println("Invalid choice, please try again");
                menu();
        }
    }
    private Subscriber getMemberID() throws UnavailableBookException {
        System.out.println("Please enter the ID of the member you wish to view: ");
        int memberID6=sc.nextInt();
        try{
            Subscriber.getSubscriberById(memberID6);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Invalid member ID, please try again");
            menu();
        }
        return Subscriber.getSubscriberById(memberID6);
    }

    private void ShowAnalytics() {
        System.out.println("----------------------General Analytics----------------------");
            System.out.println("""
                Number of books in the library: """+Book.getBooks().size()+"""
                \nNumber of available books: """+Book.numberOfAvailableBooks()+"""
                \nNumber of borrowed books: """+Book.numberOfBorrowedBooks()+"""
                \nNumber of members: """+Subscriber.getSubscribers().size()+"""
                \nNumber of staff members: """+Employee.getStaff().size()+"""
                \nTotal number of borrows since the library's opening: """+Borrow.getBorrows().size()+ """
                """);
            System.out.println("---------------------------------------------------------------------------------------------");
    }

    private void viewMemberCurrentlyBorrowedBooks() throws UnavailableBookException {
        viewAllMembers();
        Subscriber s5=getMemberID();;
        try {
            s5.printCurrentlyBorrowedBooks();

        }
        catch (NullPointerException e)
        {
            System.out.println("This member has never borrowed a book");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewMemberBorrowingHistory() throws UnavailableBookException {
        viewAllMembers();
        Subscriber s4=getMemberID();
        try {
            s4.printBorrowsHistory();
        }
        catch (NullPointerException e)
        {
            System.out.println("This member has never borrowed a book");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewMemberDetails() throws UnavailableBookException {
        viewAllMembers();
        Subscriber s3=getMemberID();
        System.out.println(s3);
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void registerBookReturn() throws UnavailableBookException {
        viewAllMembers();
        Subscriber s=getMemberID();
        Book b = getBookByID();
        try{
            s.returnBook(b);
        }
        catch (UnavailableBookException e)
        {
            System.out.println("This book is not borrowed by this member");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    private Book getBookByID() throws UnavailableBookException {
        System.out.println("Please enter the book's ID: ");
        int bookID=sc.nextInt();
        try{
            Book.getBookById(bookID);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Invalid book ID, please try again");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        return Book.getBookById(bookID);
    }

    private void registerBookBorrow() throws UnavailableBookException {
        viewAllMembers();
        Subscriber s=getMemberID();
        viewAvailableBooks();
        Book b=getBookByID();
        try{
            s.borrowBook(b);
        }
        catch (UnavailableBookException e)
        {
            System.out.println("This book is not available for borrowing");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewAllMembers() throws UnavailableBookException {
        try{
            System.out.println("-------------------------------------------Members-------------------------------------------------");
            Subscriber.printSubscribers();
        }
        catch (NullPointerException e)
        {
            System.out.println("No members registered yet");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void removeMember() throws UnavailableBookException {
        viewAllMembers();
        System.out.println("Please enter the ID of the member you wish to remove: ");
        int memberID=sc.nextInt();
        try{
            Subscriber.removeSubscriber(memberID);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Invalid ID, please try again");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void registerMember()  {
        System.out.println("Please enter the member's name: ");
        String memberName=sc.nextLine();
        memberName=sc.nextLine();
        System.out.println("Please enter the member's email: ");
        String memberEmail=sc.nextLine();
        System.out.println("Please enter the member's address: ");
        String memberAddress=sc.nextLine();
        new Subscriber(memberName,memberEmail,memberAddress);
        System.out.println("Member successfully registered !");
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void removeBookFromLibrary() throws UnavailableBookException {
        viewAllBooks();
        System.out.println("Please enter the ID of the book you wish to remove: ");
        int id=sc.nextInt();
        try{
            Book.removeBook(id);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Invalid ID, please try again");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("Book successfully removed from the library !");
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void addBookToLibrary()  {
        int genre;
        System.out.println("Please enter the book's name: ");
        String name=sc.nextLine();
        name=sc.nextLine();
        System.out.println("Please enter the book's author: ");
        String author=sc.nextLine();
        System.out.println("Please enter the book's publisher: ");
        String publisher=sc.nextLine();
        System.out.println("Please enter the book's publication year: ");
        String publicationYear=sc.nextLine();
        System.out.println("Please enter the book's description: ");
        String description=sc.nextLine();
        System.out.println("""
                Please select the book's genre:\s
                1- Fiction
                2- Non-fiction
                3- Poetry
                4- Drama
                5- History
                6- Classic literature
                7- Science
                8- Comics
                9- CookBooks
                10- Art
                11- Biography""");
        genre=sc.nextInt();
        while(genre<1 || genre>11)
        {
            System.out.println("Invalid choice, please try again");
            genre=sc.nextInt();
        }
        new Book(name,author,publisher,publicationYear,description,genres.get(genre-1));
        System.out.println("Book successfully added to the library !");
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewCurrentlyBorrowedBooks() throws UnavailableBookException {
        try{
            System.out.println("-------------------------------------------All Currently Borrowed Books-------------------------------------------------");
            Book.viewCurrentlyBorrowedBooks();
        }
        catch (UnavailableBookException e)
        {
            System.out.println("No books are currently borrowed");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void filterByGenre() throws UnavailableBookException {
        int genre;
        System.out.println("""
                Which genre do you wish to filter by:\s
                1- Fiction
                2- Non-fiction
                3- Poetry
                4- Drama
                5- History
                6- Classic literature
                7- Science
                8- Comics
                9- CookBooks
                10- Art
                11- Biography""");
        genre=sc.nextInt();
        try{
            System.out.println("-------------------------------------------"+genres.get(genre-1)+"-------------------------------------------------");
            Book.filterByGenre(genres.get(genre-1));
        }
        catch (UnavailableBookException e)
        {
            System.out.println("No books of this genre registered yet");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewAvailableBooks() throws UnavailableBookException {
        try{
            System.out.println("-------------------------------------------All Available Books-------------------------------------------------");
            Book.viewAvailableBooks();
        }
        catch (UnavailableBookException e)
        {
            System.out.println("No available books");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewAllBooks() throws UnavailableBookException {
        try{
            System.out.println("-------------------------------------------All Books-------------------------------------------------");
            Book.viewAllBooks();
        }
        catch (UnavailableBookException e)
        {
            System.out.println("No books in the library yet");
            System.out.println("---------------------------------------------------------------------------------------------");
            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void viewStaff() throws UnavailableBookException {
        try{
            System.out.println("-------------------------------------------Staff-------------------------------------------------");
            Employee.printStaff();
        }
        catch (NullPointerException e)
        {
            System.out.println("No staff members registered yet");
            System.out.println("---------------------------------------------------------------------------------------------");

            menu();
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }


}
