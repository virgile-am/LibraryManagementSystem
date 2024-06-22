package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.UserPrivilege;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPrivilegeDAO {

    // Add a new user privilege to the database
    public void addUserPrivilege(UserPrivilege userPrivilege) {
        String query = "INSERT INTO user_privilege (userID, permID) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userPrivilege.getUserID());
            preparedStatement.setInt(2, userPrivilege.getPermID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all user privileges from the database
    public List<UserPrivilege> getAllUserPrivileges() {
        List<UserPrivilege> userPrivileges = new ArrayList<>();
        String query = "SELECT * FROM user_privilege";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                UserPrivilege userPrivilege = new UserPrivilege(
                        resultSet.getInt("userID"),
                        resultSet.getInt("permID")
                );
                userPrivileges.add(userPrivilege);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return userPrivileges;
    }

    // Retrieve user privileges by userID
    public List<UserPrivilege> getUserPrivilegesByUserID(int userID) {
        List<UserPrivilege> userPrivileges = new ArrayList<>();
        String query = "SELECT * FROM user_privilege WHERE userID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    UserPrivilege userPrivilege = new UserPrivilege(
                            resultSet.getInt("userID"),
                            resultSet.getInt("permID")
                    );
                    userPrivileges.add(userPrivilege);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return userPrivileges;
    }

    // Delete user privileges by userID and permID
    public void deleteUserPrivilege(int userID, int permID) {
        String query = "DELETE FROM user_privilege WHERE userID = ? AND permID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, permID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
