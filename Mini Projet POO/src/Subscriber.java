import java.time.*;
import java.util.*;

//this class represents a subscriber(Member) in the library
public class Subscriber {
    private static long i=0;    //this is used to give each subscriber a unique id
    private String name;
    private String email;
    private String address;
    private long id;
    private static List<Subscriber> subscribers= new ArrayList<>(); //this is used to store all the subscribers in the library
    private List<Borrow> currentlyBorrowedBooks; //this is used to store all the books that the subscriber is currently borrowing
    private List<Borrow> borrowsHistory;     //this is used to store all the books that the subscriber borrowed in the past(and returned)
    public Subscriber(String name, String email, String address)
    {
        i++;
        this.name=name;
        this.email=email;
        this.address=address;
        this.id=i;
        currentlyBorrowedBooks= new ArrayList<>();
        borrowsHistory= new ArrayList<>();
        subscribers.add(this);
    }
    public String getName() {
        return name;
    }
    public double getId() {
        return id;
    }

    public static List<Subscriber> getSubscribers() {
        return subscribers;
    }

    //returns a subscriber by his id
    public static Subscriber getSubscriberById(int x) throws NoSuchElementException
    {
        for(Subscriber i:subscribers)
        {
            if(i.getId()==x)
            {
                return i;
            }
        }
        throw new NoSuchElementException();  //if the subscriber is not found
    }

    //display all the subscribers in the library
    public static void printSubscribers()
    {
        if(subscribers.isEmpty())
        {
            throw new NullPointerException();   //if there are no subscribers in the library
        }
        for(Subscriber i:subscribers)
        {
            System.out.println("--->"+i);
        }
    }

    //display all the books that this subscriber is currently borrowing
    public void printCurrentlyBorrowedBooks()
    {
        if(this.currentlyBorrowedBooks.isEmpty())
        {
            throw new NullPointerException();   //if there are no books that the subscriber is currently borrowing
        }
        for(Borrow b:this.currentlyBorrowedBooks)
        {
            System.out.println(b.toStringForSubscriber());
        }
    }

    //display all the books that this subscriber borrowed in the past(and returned)
    public void printBorrowsHistory()
    {
        if(this.borrowsHistory.isEmpty())
        {
            throw new NullPointerException();   //if there are no books that the subscriber borrowed in the past(and returned)
        }
        for(Borrow b:this.borrowsHistory)
        {
            System.out.println(b.toStringForSubscriber());
        }
    }

    //returns a borrow instance by a book instance
    public Borrow getBorrowByBook(Book book)
    {
        if(currentlyBorrowedBooks.isEmpty() || !book.isBorrowed() )
        {
            return null;
        }
        for(Borrow i:currentlyBorrowedBooks)
        {
            if(i.getBook().equals(book))
            {
                return i;
            }
        }
        return null;
    }

    //This method is used to borrow a book
    public void borrowBook(Book b) throws UnavailableBookException{
        if (b.isBorrowed()) {
            throw new UnavailableBookException();   //if the book is already borrowed
        } else {
            Borrow borrow = new Borrow(this, b);    //create a new borrow instance
            b.setBorrowed(true);    //set the book as borrowed
            this.currentlyBorrowedBooks.add(borrow);    //add the borrow instance to the list of the currently borrowed books of the subscriber
            System.out.println("Book borrowed successfully");
        }
    }

    //This method is used to return a book
    public void returnBook(Book b) throws UnavailableBookException{
        Borrow br=this.getBorrowByBook(b);  //get the borrow instance of the book
        if(br==null)
        {
            throw new UnavailableBookException();   //if the book is not borrowed by the subscriber
        }
        b.setBorrowed(false);   //set the book as no longer borrowed
        this.currentlyBorrowedBooks.remove(br);  //remove the borrow instance from the list of the currently borrowed books of the subscriber
        this.borrowsHistory.add(br);    //add the borrow instance to the list of the borrows history of the subscriber
        br.setReturnDate(LocalDateTime.now());  //set the return date of the borrow instance
        System.out.println("Book returned successfully");
    }

    //This method is used to remove a subscriber from the members database of the library by its id
    public static void removeSubscriber(int x) throws NoSuchElementException
    {
        Subscriber s=Subscriber.getSubscriberById(x);   //if the subscriber is not found it will throw a NoSuchElementException that comes from the getSubscriberById method
        subscribers.remove(s);
        System.out.println("Subscriber removed successfully");
    }

    //returns a string representation of the subscriber
    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' ;
    }

}
