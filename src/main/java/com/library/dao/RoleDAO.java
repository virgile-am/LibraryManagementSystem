package src.main.java.com.library.dao;
import src.main.java.com.library.config.DatabaseConfig;
import src.main.java.com.library.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {

    // Add a new role to the database
    public void addRole(Role role) {
        String query = "INSERT INTO role (roleID, roleName) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, role.getRoleID());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Retrieve a list of all roles from the database
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM role";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt("roleID"),
                        resultSet.getString("roleName")
                );
                roles.add(role);
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return roles;
    }

    // Retrieve a role by its ID
    public Role getRoleById(int roleId) {
        Role role = null;
        String query = "SELECT * FROM role WHERE roleID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roleId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    role = new Role(
                            resultSet.getInt("roleID"),
                            resultSet.getString("roleName")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
        return role;
    }

    // Update the details of an existing role
    public void updateRole(Role role) {
        String query = "UPDATE role SET roleName = ? WHERE roleID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setInt(2, role.getRoleID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }

    // Delete a role from the database by its ID
    public void deleteRole(int roleId) {
        String query = "DELETE FROM role WHERE roleID = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception
            e.printStackTrace();
        }
    }
}
