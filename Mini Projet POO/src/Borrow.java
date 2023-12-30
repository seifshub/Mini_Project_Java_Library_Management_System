import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
 //The borrow class represents a borrowing operation that occurred in the library
//It stores the subscriber who borrowed the book, the book itself, the borrow date and the return date
public class Borrow {
    private Subscriber subscriber;
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private static List<Borrow> borrows= new ArrayList<>(); //this is used to store all the borrows that occurred in the library
    public Borrow(Subscriber subscriber, Book book)
    {
        this.subscriber=subscriber;
        this.book=book;
        this.borrowDate=LocalDateTime.now();
        this.returnDate=null;
        borrows.add(this);
    }
    public Book getBook() {
        return book;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public static List<Borrow> getBorrows() {
        return borrows;
    }

    //display all the borrows that occurred in the library
    public static void printBorrows()
    {
        if(borrows.isEmpty())
        {
            System.out.println("No borrows yet");
            return;
        }
        for(Borrow i:borrows)
        {
            System.out.println("--->"+i);
        }
    }

    //returns a string representation of the borrow
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String borrowDate = this.borrowDate.format(formatter);
        String returnDate;
        if(this.returnDate==null)
        {
            returnDate="Not returned yet";
        }
        else
        {
            returnDate=this.returnDate.format(formatter);
        }
        return "Borrowed by: " + subscriber.getName() +
                ", Book: " + book.getTitle() +
                ", Borrow Date: " + borrowDate +
                ", Return Date: " + returnDate;
    }

    //returns a string representation of the borrow but without the subscriber's name (used when displaying the borrows history of a subscriber)
    public String toStringForSubscriber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String borrowDate = this.borrowDate.format(formatter);
        String returnDate;
        if(this.returnDate==null)
        {
            returnDate="Not returned yet";
        }
        else
        {
            returnDate=this.returnDate.format(formatter);
        }
        return "Book: " + book.getTitle() +
                ", Borrow Date: " + borrowDate +
                ", Return Date: " + returnDate;
    }


}
