package com.library.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book bookWithoutID;
    private Book bookWithID;

    @BeforeEach
    public void setUp() {
        // Initialize books with different constructors
        bookWithoutID = new Book("Title", "Author", "Publisher", 2023, "1234567890", "Category", 10);
        bookWithID = new Book(1, "Title", "Author", "Publisher", 2023, "1234567890", "Category", 10);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("Title", bookWithoutID.getTitle());
        assertEquals("Author", bookWithoutID.getAuthor());
        assertEquals("Publisher", bookWithoutID.getPublisher());
        assertEquals(2023, bookWithoutID.getYear());
        assertEquals("1234567890", bookWithoutID.getIsbn());
        assertEquals("Category", bookWithoutID.getCategory());
        assertEquals(10, bookWithoutID.getQuantity());

        // Test setters
        bookWithoutID.setTitle("New Title");
        bookWithoutID.setAuthor("New Author");
        bookWithoutID.setPublisher("New Publisher");
        bookWithoutID.setYear(2024);
        bookWithoutID.setIsbn("0987654321");
        bookWithoutID.setCategory("New Category");
        bookWithoutID.setQuantity(15);

        assertEquals("New Title", bookWithoutID.getTitle());
        assertEquals("New Author", bookWithoutID.getAuthor());
        assertEquals("New Publisher", bookWithoutID.getPublisher());
        assertEquals(2024, bookWithoutID.getYear());
        assertEquals("0987654321", bookWithoutID.getIsbn());
        assertEquals("New Category", bookWithoutID.getCategory());
        assertEquals(15, bookWithoutID.getQuantity());
    }

    @Test
    public void testBookIDProperty() {
        assertEquals(1, bookWithID.getBookID());
        bookWithID.setBookID(2);
        assertEquals(2, bookWithID.getBookID());
    }

    @Test
    public void testToString() {
        assertEquals("Title", bookWithID.toString());
    }

    @Test
    public void testPropertyMethods() {
        assertEquals("Title", bookWithID.titleProperty().get());
        assertEquals("Author", bookWithID.authorProperty().get());
        assertEquals("Publisher", bookWithID.publisherProperty().get());
        assertEquals(2023, bookWithID.yearProperty().get());
        assertEquals("1234567890", bookWithID.isbnProperty().get());
        assertEquals("Category", bookWithID.categoryProperty().get());
        assertEquals(10, bookWithID.quantityProperty().get());
    }
}
