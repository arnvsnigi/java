import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Book {
    private static int nextBookId = 1;

    private int bookId;
    private String title;
    private String author;
    String publisher;
    private double price;

    public Book(String title, String author, String publisher, double price) {
        this.bookId = nextBookId++;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Publisher: " + publisher + ", Price: $" + price;
    }
}

public class pgm2 {
    private List<Book> books;
    private List<Book> sortedBooks;

    public pgm2() {
        books = new ArrayList<>();
        sortedBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        sortedBooks.add(book);
        Collections.sort(sortedBooks, (b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));
    }

    public void printAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void printBooksSortedByPrice() {
        for (Book book : sortedBooks) {
            System.out.println(book);
        }
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksAbovePrice(double minPrice) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPrice() > minPrice) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchBooksByTitle(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getBooksByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        return result;
    }

    public void updatePublisher(String title, String newPublisher) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.publisher = newPublisher;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        pgm2 bookDatabase = new pgm2();

        // Adding sample books to the database
        bookDatabase.addBook(new Book("Java Programming", "John Smith", "ABC Publications", 35.99));
        bookDatabase.addBook(new Book("Data Structures", "Jane Doe", "XYZ Publishers", 25.50));
        bookDatabase.addBook(new Book("Algorithms", "John Smith", "ABC Publications", 40.75));
        bookDatabase.addBook(new Book("Web Development", "Alice Johnson", "XYZ Publishers", 30.50));

        // Print all books
        System.out.println("All Books:");
        bookDatabase.printAllBooks();
        System.out.println();

        // Print books sorted by price
        System.out.println("Books Sorted by Price:");
        bookDatabase.printBooksSortedByPrice();
        System.out.println();

        // Prompt for an author name and list all books with the same author's name
        System.out.print("Enter author name to search: ");
        String authorName = scanner.nextLine();
        List<Book> authorBooks = bookDatabase.getBooksByAuthor(authorName);
        System.out.println("Books by Author " + authorName + ":");
        for (Book book : authorBooks) {
            System.out.println(book);
        }
        System.out.println();

        // Create a new list holding all book details with price greater than a user-specified price
        System.out.print("Enter minimum price to filter books: ");
        double minPrice = scanner.nextDouble();
        List<Book> booksAbovePrice = bookDatabase.getBooksAbovePrice(minPrice);
        System.out.println("Books with price greater than $" + minPrice + ":");
        for (Book book : booksAbovePrice) {
            System.out.println(book);
        }
        System.out.println();

        // For a given value by the user, find all books that match either the whole or a part of the book title
        scanner.nextLine(); // Consume the newline left in the buffer
        System.out.print("Enter keyword to search in book titles: ");
        String keyword = scanner.nextLine();
        List<Book> titleMatchBooks = bookDatabase.searchBooksByTitle(keyword);
        System.out.println("Books with title containing \"" + keyword + "\":");
        for (Book book : titleMatchBooks) {
            System.out.println(book);
        }
        System.out.println();

        // Identify a publisher and print books from a particular publisher
        System.out.print("Enter publisher name to search: ");
        String publisherName = scanner.nextLine();
        List<Book> publisherBooks = bookDatabase.getBooksByPublisher(publisherName);
        System.out.println("Books from Publisher " + publisherName + ":");
        for (Book book : publisherBooks) {
            System.out.println(book);
        }
        System.out.println();

        // Update the publisher details based on a title
        System.out.print("Enter title of the book to update publisher: ");
        String titleToUpdate = scanner.nextLine();
        System.out.print("Enter new publisher name: ");
        String newPublisher = scanner.nextLine();
        bookDatabase.updatePublisher(titleToUpdate, newPublisher);

        // Print all books after the update
        System.out.println("All Books after Publisher Update:");
        bookDatabase.printAllBooks();
    }
}
