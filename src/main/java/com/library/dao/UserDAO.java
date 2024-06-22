package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // Add a new user to the database
    public void addUser(User user) {
        String query = "INSERT INTO users (userid, username, password, roleid) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all users from the database
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT userid, username, password, roleid FROM users";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("userid"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("roleid")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return users;
    }

    // Retrieve a user by their ID
    public User getUserById(int userId) {
        User user = null;
        String query = "SELECT userid, username, password, roleid FROM users WHERE userid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("userid"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getInt("roleid")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return user;
    }

    // Update the details of an existing user
    public void updateUser(User user) {
        String query = "UPDATE users SET username = ?, password = ?, roleid = ? WHERE userid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoleID());
            preparedStatement.setInt(4, user.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Delete a user from the database by their ID
    public void deleteUser(int userId) {
        String query = "DELETE FROM users WHERE userid = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
