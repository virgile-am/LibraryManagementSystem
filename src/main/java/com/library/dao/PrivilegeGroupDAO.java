package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.PrivilegeGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrivilegeGroupDAO {

    // Add a new privilege group to the database
    public void addPrivilegeGroup(PrivilegeGroup privilegeGroup) {
        String query = "INSERT INTO privilege_group (groupID, groupName) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, privilegeGroup.getGroupID());
            preparedStatement.setString(2, privilegeGroup.getGroupName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all privilege groups from the database
    public List<PrivilegeGroup> getAllPrivilegeGroups() {
        List<PrivilegeGroup> privilegeGroups = new ArrayList<>();
        String query = "SELECT * FROM privilege_group";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                PrivilegeGroup privilegeGroup = new PrivilegeGroup(
                        resultSet.getInt("groupID"),
                        resultSet.getString("groupName")
                );
                privilegeGroups.add(privilegeGroup);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return privilegeGroups;
    }

    // Retrieve a privilege group by its ID
    public PrivilegeGroup getPrivilegeGroupById(int groupId) {
        PrivilegeGroup privilegeGroup = null;
        String query = "SELECT * FROM privilege_group WHERE groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    privilegeGroup = new PrivilegeGroup(
                            resultSet.getInt("groupID"),
                            resultSet.getString("groupName")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return privilegeGroup;
    }

    // Update the details of an existing privilege group
    public void updatePrivilegeGroup(PrivilegeGroup privilegeGroup) {
        String query = "UPDATE privilege_group SET groupName = ? WHERE groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, privilegeGroup.getGroupName());
            preparedStatement.setInt(2, privilegeGroup.getGroupID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Delete a privilege group from the database by its ID
    public void deletePrivilegeGroup(int groupId) {
        String query = "DELETE FROM privilege_group WHERE groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
