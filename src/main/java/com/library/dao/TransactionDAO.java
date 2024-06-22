package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAO {

    // Add a new transaction to the database
    public void addTransaction(Transaction transaction) {
        String query = "INSERT INTO transaction (bookID, patronID, issueDate, dueDate, returnDate, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transaction.getBookID());
            preparedStatement.setInt(2, transaction.getPatronID());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getIssueDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(transaction.getDueDate().getTime()));
            preparedStatement.setDate(5, transaction.getReturnDate() != null ?
                    new java.sql.Date(transaction.getReturnDate().getTime()) : null);
            preparedStatement.setString(6, transaction.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a list of all transactions from the database
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        resultSet.getInt("transactionID"),
                        resultSet.getInt("bookID"),
                        resultSet.getInt("patronID"),
                        resultSet.getDate("issueDate"),
                        resultSet.getDate("dueDate"),
                        resultSet.getDate("returnDate"),
                        resultSet.getString("status")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Retrieve a transaction by its ID
    public Transaction getTransactionById(int transactionId) {
        Transaction transaction = null;
        String query = "SELECT * FROM transaction WHERE transactionID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transactionId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    transaction = new Transaction(
                            resultSet.getInt("transactionID"),
                            resultSet.getInt("bookID"),
                            resultSet.getInt("patronID"),
                            resultSet.getDate("issueDate"),
                            resultSet.getDate("dueDate"),
                            resultSet.getDate("returnDate"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    // Update the details of an existing transaction
    public void updateTransaction(Transaction transaction) {
        String query = "UPDATE transaction SET bookID = ?, patronID = ?, issueDate = ?, " +
                "dueDate = ?, returnDate = ?, status = ? WHERE transactionID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transaction.getBookID());
            preparedStatement.setInt(2, transaction.getPatronID());
            preparedStatement.setDate(3, new java.sql.Date(transaction.getIssueDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(transaction.getDueDate().getTime()));
            preparedStatement.setDate(5, transaction.getReturnDate() != null ?
                    new java.sql.Date(transaction.getReturnDate().getTime()) : null);
            preparedStatement.setString(6, transaction.getStatus());
            preparedStatement.setInt(7, transaction.getTransactionID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a transaction from the database by its ID
    public void deleteTransaction(int transactionId) {
        String query = "DELETE FROM transaction WHERE transactionID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, transactionId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
