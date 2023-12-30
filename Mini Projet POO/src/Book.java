
import java.util.*;

//The Book class represents a book in a library.

public class Book {
    private static long i=0; //this is used to give each book a unique id
    private long id;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private String description;
    private boolean borrowed;
    private String genre;
    static List<Book> books= new ArrayList<>(); //this is used to store all the books in the library
    public Book(String title, String author, String publisher, String publicationYear, String description,String genre)
    {
        i++;
        this.id=i;
        this.title=title;
        this.author=author;
        this.publisher=publisher;
        this.publicationYear=publicationYear;
        this.description=description;
        this.borrowed=false;
        this.genre=genre;
        books.add(this);
    }

    //Returns the number of borrowed books in the library
    public static long numberOfBorrowedBooks() {
        long x=0;
        for(Book b:books)
        {
            if(b.isBorrowed())
                x++;
        }
        return x;
    }

    //Returns the number of available books in the library
    public static long numberOfAvailableBooks()
    {
        long x=0;
        for(Book b:books)
        {
            if(!b.isBorrowed())
                x++;
        }
        return x;
    }

    //getters and setters...
    public long getId() {
        return id;
    }
    public static List<Book> getBooks() {
        return books;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public boolean isBorrowed() {
        return borrowed;
    }
    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    //Returns a string representation of the book
    @Override
    public String toString() {
        return  "ID=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publicationYear=" + publicationYear +
                ", description='" + description + '\'' +
                ", borrowed=" + borrowed +
                ", genre='" + genre + '\'' ;
    }

    //Returns a book by its id
    public static Book getBookById(int x) throws NoSuchElementException
    {
        for(Book i:books)
        {
            if(i.getId()==x)
                return i;
        }
        throw new NoSuchElementException(); //if the book is not found
    }

    //removes a book from the library by its id
    public static void removeBook(int x)
    {
        Book b=Book.getBookById(x); //if the book is not found it will throw a NoSuchElementException that comes from the getBookById method
        if(b.isBorrowed())
        {
            System.out.println("This book is currently borrowed, it can't be removed");
        }
        else
            books.remove(b);
    }

    //display all the books in the library
    public static void viewAllBooks() throws UnavailableBookException {
        if(books.isEmpty())
            throw new UnavailableBookException();   //if there are no books in the library
        for(Book b:books)
        {
            System.out.println("---"+b.toString());
        }
    }

    //display all the available books in the library
    public static void viewAvailableBooks() throws UnavailableBookException {
        String s="";
        for(Book b:books)
        {
            if(!b.isBorrowed())
            {
                s+="--->"+ b +"\n";
            }
        }
        if(s.isEmpty())
            throw new UnavailableBookException();   //if there are no available books in the library
        System.out.println(s);
    }

    //display the books that have a specific genre
    public static void filterByGenre(String genre) throws UnavailableBookException {
        String s="";
        for(Book b:books)
        {
            if(Objects.equals(b.getGenre(), genre))
                s+= "--->"+b +"\n";
        }
        if(s.isEmpty())
            throw new UnavailableBookException();   //if there are no books with this genre in the library
        System.out.println(s);
    }

    //display all the currently borrowed books in the library
    public static void viewCurrentlyBorrowedBooks() throws UnavailableBookException {
        String s="";
        for(Book b:books)
        {
            if(b.isBorrowed())
                s+= "--->"+b +"\n";
        }
        if(s.isEmpty())
            throw new UnavailableBookException();   //if there are no currently borrowed books in the library
        System.out.println(s);
    }


}
