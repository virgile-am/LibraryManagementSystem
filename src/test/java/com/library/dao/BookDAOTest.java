package com.library.dao;

import com.library.config.DatabaseConfig;
import com.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BookDAOTest {

    private BookDAO bookDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Create mocks for the dependencies
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        // Set up the BookDAO instance with mocked database connection
        bookDAO = new BookDAO();

        // Mock DatabaseConfig.getConnection() to return our mock connection
        Mockito.mockStatic(DatabaseConfig.class);
        when(DatabaseConfig.getConnection()).thenReturn(connection);

        // Mock behavior for PreparedStatement and ResultSet
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    @Test
    public void testAddBook() throws SQLException {
        // Prepare a Book instance
        Book book = new Book("Title", "Author", "Publisher", 2021, "1234567890", "Category", 5);

        // Execute the method under test
        bookDAO.addBook(book);

        // Verify that PreparedStatement was called with correct parameters
        verify(preparedStatement).setString(1, book.getTitle());
        verify(preparedStatement).setString(2, book.getAuthor());
        verify(preparedStatement).setString(3, book.getPublisher());
        verify(preparedStatement).setInt(4, book.getYear());
        verify(preparedStatement).setString(5, book.getIsbn());
        verify(preparedStatement).setString(6, book.getCategory());
        verify(preparedStatement).setInt(7, book.getQuantity());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetAllBooks() throws SQLException {
        // Prepare mock data for ResultSet
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("bookid")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Title");
        when(resultSet.getString("author")).thenReturn("Author");
        when(resultSet.getString("publisher")).thenReturn("Publisher");
        when(resultSet.getInt("year")).thenReturn(2021);
        when(resultSet.getString("isbn")).thenReturn("1234567890");
        when(resultSet.getString("category")).thenReturn("Category");
        when(resultSet.getInt("quantity")).thenReturn(5);

        // Set ResultSet on PreparedStatement
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Execute the method under test
        List<Book> books = bookDAO.getAllBooks();

        // Verify the result
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals(1, book.getBookID());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("Publisher", book.getPublisher());
        assertEquals(2021, book.getYear());
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Category", book.getCategory());
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testGetBookById() throws SQLException {
        // Prepare mock data for ResultSet
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("bookid")).thenReturn(1);
        when(resultSet.getString("title")).thenReturn("Title");
        when(resultSet.getString("author")).thenReturn("Author");
        when(resultSet.getString("publisher")).thenReturn("Publisher");
        when(resultSet.getInt("year")).thenReturn(2021);
        when(resultSet.getString("isbn")).thenReturn("1234567890");
        when(resultSet.getString("category")).thenReturn("Category");
        when(resultSet.getInt("quantity")).thenReturn(5);

        // Set ResultSet on PreparedStatement
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Execute the method under test
        Book book = bookDAO.getBookById(1);

        // Verify the result
        assertNotNull(book);
        assertEquals(1, book.getBookID());
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("Publisher", book.getPublisher());
        assertEquals(2021, book.getYear());
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Category", book.getCategory());
        assertEquals(5, book.getQuantity());
    }

    @Test
    public void testUpdateBook() throws SQLException {
        // Prepare a Book instance
        Book book = new Book(1, "Updated Title", "Updated Author", "Updated Publisher", 2022, "0987654321", "Updated Category", 10);

        // Execute the method under test
        bookDAO.updateBook(book);

        // Verify that PreparedStatement was called with correct parameters
        verify(preparedStatement).setString(1, book.getTitle());
        verify(preparedStatement).setString(2, book.getAuthor());
        verify(preparedStatement).setString(3, book.getPublisher());
        verify(preparedStatement).setInt(4, book.getYear());
        verify(preparedStatement).setString(5, book.getIsbn());
        verify(preparedStatement).setString(6, book.getCategory());
        verify(preparedStatement).setInt(7, book.getQuantity());
        verify(preparedStatement).setInt(8, book.getBookID());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteBook() throws SQLException {
        // Execute the method under test
        bookDAO.deleteBook(1);

        // Verify that PreparedStatement was called with correct parameter
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).executeUpdate();
    }
}
