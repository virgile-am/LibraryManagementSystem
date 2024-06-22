package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.Patron;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDAO {

    // Add a new patron to the database
    public void addPatron(Patron patron) {
        String query = "INSERT INTO patron (patronid, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patron.getPatronID());
            preparedStatement.setString(2, patron.getName());
            preparedStatement.setString(3, patron.getEmail());
            preparedStatement.setString(4, patron.getPhone());
            preparedStatement.setString(5, patron.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all patrons from the database
    public List<Patron> getAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        String query = "SELECT * FROM patron";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Patron patron = new Patron(
                        resultSet.getInt("patronid"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );
                patrons.add(patron);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return patrons;
    }

    // Retrieve a patron by their ID
    public Patron getPatronById(int patronId) {
        Patron patron = null;
        String query = "SELECT * FROM patron WHERE patronid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patronId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    patron = new Patron(
                            resultSet.getInt("patronid"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return patron;
    }

    // Update the details of an existing patron
    public void updatePatron(Patron patron) {
        String query = "UPDATE patron SET name = ?, email = ?, phone = ?, address = ? WHERE patronid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patron.getName());
            preparedStatement.setString(2, patron.getEmail());
            preparedStatement.setString(3, patron.getPhone());
            preparedStatement.setString(4, patron.getAddress());
            preparedStatement.setInt(5, patron.getPatronID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Delete a patron from the database by their ID
    public void deletePatron(int patronId) {
        String query = "DELETE FROM patron WHERE patronid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patronId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
