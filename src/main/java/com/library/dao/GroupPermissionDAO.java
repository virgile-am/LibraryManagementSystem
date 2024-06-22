package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.GroupPermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupPermissionDAO {

    // Add a new group permission to the database
    public void addGroupPermission(GroupPermission groupPermission) {
        String query = "INSERT INTO group_permission (groupID, permID) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupPermission.getGroupID());
            preparedStatement.setInt(2, groupPermission.getPermID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all group permissions from the database
    public List<GroupPermission> getAllGroupPermissions() {
        List<GroupPermission> groupPermissions = new ArrayList<>();
        String query = "SELECT * FROM group_permission";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                GroupPermission groupPermission = new GroupPermission(
                        resultSet.getInt("groupID"),
                        resultSet.getInt("permID")
                );
                groupPermissions.add(groupPermission);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return groupPermissions;
    }

    // Retrieve group permissions by group ID
    public List<GroupPermission> getGroupPermissionsByGroupID(int groupID) {
        List<GroupPermission> groupPermissions = new ArrayList<>();
        String query = "SELECT * FROM group_permission WHERE groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GroupPermission groupPermission = new GroupPermission(
                            resultSet.getInt("groupID"),
                            resultSet.getInt("permID")
                    );
                    groupPermissions.add(groupPermission);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return groupPermissions;
    }

    // Retrieve group permissions by permission ID
    public List<GroupPermission> getGroupPermissionsByPermID(int permID) {
        List<GroupPermission> groupPermissions = new ArrayList<>();
        String query = "SELECT * FROM group_permission WHERE permID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, permID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    GroupPermission groupPermission = new GroupPermission(
                            resultSet.getInt("groupID"),
                            resultSet.getInt("permID")
                    );
                    groupPermissions.add(groupPermission);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return groupPermissions;
    }

    // Delete group permissions by group ID and permission ID
    public void deleteGroupPermission(int groupID, int permID) {
        String query = "DELETE FROM group_permission WHERE groupID = ? AND permID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupID);
            preparedStatement.setInt(2, permID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
