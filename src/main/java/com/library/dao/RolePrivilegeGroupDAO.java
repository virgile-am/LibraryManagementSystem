package src.main.java.com.library.dao;

import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.RolePrivilegeGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolePrivilegeGroupDAO {

    // Add a new role privilege group to the database
    public void addRolePrivilegeGroup(RolePrivilegeGroup rolePrivilegeGroup) {
        String query = "INSERT INTO role_privilege_group (roleID, groupID) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rolePrivilegeGroup.getRoleID());
            preparedStatement.setInt(2, rolePrivilegeGroup.getGroupID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all role privilege groups from the database
    public List<RolePrivilegeGroup> getAllRolePrivilegeGroups() {
        List<RolePrivilegeGroup> rolePrivilegeGroups = new ArrayList<>();
        String query = "SELECT * FROM role_privilege_group";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                RolePrivilegeGroup rolePrivilegeGroup = new RolePrivilegeGroup(
                        resultSet.getInt("roleID"),
                        resultSet.getInt("groupID")
                );
                rolePrivilegeGroups.add(rolePrivilegeGroup);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return rolePrivilegeGroups;
    }

    // Retrieve role privilege groups by role ID
    public List<RolePrivilegeGroup> getRolePrivilegeGroupsByRoleID(int roleID) {
        List<RolePrivilegeGroup> rolePrivilegeGroups = new ArrayList<>();
        String query = "SELECT * FROM role_privilege_group WHERE roleID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roleID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RolePrivilegeGroup rolePrivilegeGroup = new RolePrivilegeGroup(
                            resultSet.getInt("roleID"),
                            resultSet.getInt("groupID")
                    );
                    rolePrivilegeGroups.add(rolePrivilegeGroup);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return rolePrivilegeGroups;
    }

    // Retrieve role privilege groups by group ID
    public List<RolePrivilegeGroup> getRolePrivilegeGroupsByGroupID(int groupID) {
        List<RolePrivilegeGroup> rolePrivilegeGroups = new ArrayList<>();
        String query = "SELECT * FROM role_privilege_group WHERE groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, groupID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    RolePrivilegeGroup rolePrivilegeGroup = new RolePrivilegeGroup(
                            resultSet.getInt("roleID"),
                            resultSet.getInt("groupID")
                    );
                    rolePrivilegeGroups.add(rolePrivilegeGroup);
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return rolePrivilegeGroups;
    }

    // Delete role privilege groups by role ID and group ID
    public void deleteRolePrivilegeGroup(int roleID, int groupID) {
        String query = "DELETE FROM role_privilege_group WHERE roleID = ? AND groupID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roleID);
            preparedStatement.setInt(2, groupID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
