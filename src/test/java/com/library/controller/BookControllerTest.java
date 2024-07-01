package com.library.controller;

import com.library.dao.BookDAO;
import com.library.model.Book;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
class BookControllerTest {

    @Mock
    private BookDAO bookDAO;
    @Mock
    private TextField titleField;
    @Mock
    private TextField authorField;
    @Mock
    private TextField publisherField;
    @Mock
    private TextField yearField;
    @Mock
    private TextField isbnField;
    @Mock
    private TextField categoryField;
    @Mock
    private TextField quantityField;
    @Mock
    private TableView<Book> bookTable;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleAddBook() {
        // Set up mock data
        when(titleField.getText()).thenReturn("Effective Java");
        when(authorField.getText()).thenReturn("Joshua Bloch");
        when(publisherField.getText()).thenReturn("Addison-Wesley");
        when(yearField.getText()).thenReturn("2018");
        when(isbnField.getText()).thenReturn("978-0134685991");
        when(categoryField.getText()).thenReturn("Programming");
        when(quantityField.getText()).thenReturn("10");

        // Call the method under test
        bookController.handleAddBook();

        // Verify interactions
        verify(bookDAO, times(1)).addBook(any(Book.class));
        verify(bookTable, times(1)).getItems();
    }

    @Test
    void testHandleUpdateBook() {
        // Set up mock data
        Book mockBook = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", 2018, "978-0134685991", "Programming", 10);
        when(bookTable.getSelectionModel().getSelectedItem()).thenReturn(mockBook);
        when(titleField.getText()).thenReturn("Effective Java 3rd Edition");
        when(authorField.getText()).thenReturn("Joshua Bloch");
        when(publisherField.getText()).thenReturn("Addison-Wesley");
        when(yearField.getText()).thenReturn("2018");
        when(isbnField.getText()).thenReturn("978-0134685991");
        when(categoryField.getText()).thenReturn("Programming");
        when(quantityField.getText()).thenReturn("10");

        // Call the method under test
        bookController.handleUpdateBook();

        // Verify interactions
        verify(bookDAO, times(1)).updateBook(mockBook);
        verify(bookTable, times(1)).getItems();
    }

    @Test
    void testLoadBooks() {
        // Set up mock data
        Book book1 = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", 2018, "978-0134685991", "Programming", 10);
        Book book2 = new Book("Clean Code", "Robert C. Martin", "Prentice Hall", 2008, "978-0132350884", "Programming", 5);
        when(bookDAO.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        // Call the method under test
        bookController.loadBooks();

        // Verify interactions
        verify(bookTable, times(1)).getItems();
        assertEquals(2, bookTable.getItems().size());
    }
}
