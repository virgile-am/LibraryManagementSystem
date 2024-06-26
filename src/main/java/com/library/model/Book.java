package src.main.java.com.library.model;

import javafx.beans.property.*;

public class Book {
    private final IntegerProperty bookID;
    private final StringProperty title;
    private final StringProperty author;
    private final StringProperty publisher;
    private final IntegerProperty year;
    private final StringProperty isbn;
    private final StringProperty category;
    private final IntegerProperty quantity;

    // Constructor for new books without ID (for adding)
    public Book(String title, String author, String publisher, int year, String isbn, String category, int quantity) {
        this.bookID = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.year = new SimpleIntegerProperty(year);
        this.isbn = new SimpleStringProperty(isbn);
        this.category = new SimpleStringProperty(category);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    // Constructor for existing books with ID (for retrieval)
    public Book(int bookID, String title, String author, String publisher, int year, String isbn, String category, int quantity) {
        this.bookID = new SimpleIntegerProperty(bookID);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.year = new SimpleIntegerProperty(year);
        this.isbn = new SimpleStringProperty(isbn);
        this.category = new SimpleStringProperty(category);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    // Getters and setters for properties
    public int getBookID() {
        return bookID.get();
    }

    public void setBookID(int bookID) {
        this.bookID.set(bookID);
    }

    public IntegerProperty bookIDProperty() {
        return bookID;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getPublisher() {
        return publisher.get();
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public int getYear() {
        return year.get();
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public String getIsbn() {
        return isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    // Override toString method to return the title
    @Override
    public String toString() {
        return getTitle();
    }
}
