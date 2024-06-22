package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    // Add a new book to the database
    public void addBook(Book book) {
        String query = "INSERT INTO book (title, author, publisher, year, isbn, category, quantity) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setString(5, book.getIsbn());
            preparedStatement.setString(6, book.getCategory());
            preparedStatement.setInt(7, book.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a list of all books from the database
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("bookid"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getInt("year"),
                        resultSet.getString("isbn"),
                        resultSet.getString("category"),
                        resultSet.getInt("quantity")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Retrieve a book by its ID
    public Book getBookById(int bookId) {
        Book book = null;
        String query = "SELECT * FROM book WHERE bookid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    book = new Book(
                            resultSet.getInt("bookid"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("publisher"),
                            resultSet.getInt("year"),
                            resultSet.getString("isbn"),
                            resultSet.getString("category"),
                            resultSet.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // Update the details of an existing book
    public void updateBook(Book book) {
        String query = "UPDATE book SET title = ?, author = ?, publisher = ?, year = ?, isbn = ?, category = ?, quantity = ? WHERE bookid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setString(5, book.getIsbn());
            preparedStatement.setString(6, book.getCategory());
            preparedStatement.setInt(7, book.getQuantity());
            preparedStatement.setInt(8, book.getBookID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a book from the database by its ID
    public void deleteBook(int bookId) {
        String query = "DELETE FROM book WHERE bookid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}