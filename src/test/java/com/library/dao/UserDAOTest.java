package com.library.dao;

import com.library.config.DatabaseConfig;
import com.library.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    private UserDAO userDAO;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        // Create mocks for the dependencies
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        // Set up the UserDAO instance with mocked database connection
        userDAO = new UserDAO();

        // Mock DatabaseConfig.getConnection() to return our mock connection
        Mockito.mockStatic(DatabaseConfig.class);
        when(DatabaseConfig.getConnection()).thenReturn(connection);

        // Mock behavior for PreparedStatement and ResultSet
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
    }

    @Test
    public void testAddUser() throws SQLException {
        // Prepare a User instance
        User user = new User(1, "user1", "password1", 1);

        // Execute the method under test
        userDAO.addUser(user);

        // Verify that PreparedStatement was called with correct parameters
        verify(preparedStatement).setInt(1, user.getUserID());
        verify(preparedStatement).setString(2, user.getUsername());
        verify(preparedStatement).setString(3, user.getPassword());
        verify(preparedStatement).setInt(4, user.getRoleID());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        // Prepare mock data for ResultSet
        when(resultSet.next()).thenReturn(true, true, false);
        when(resultSet.getInt("userid")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("user1");
        when(resultSet.getString("password")).thenReturn("password1");
        when(resultSet.getInt("roleid")).thenReturn(1);

        // Set ResultSet on PreparedStatement
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Execute the method under test
        List<User> users = userDAO.getAllUsers();

        // Verify the result
        assertEquals(2, users.size());
        User user = users.get(0);
        assertEquals(1, user.getUserID());
        assertEquals("user1", user.getUsername());
        assertEquals("password1", user.getPassword());
        assertEquals(1, user.getRoleID());
    }

    @Test
    public void testGetUserById() throws SQLException {
        // Prepare mock data for ResultSet
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("userid")).thenReturn(1);
        when(resultSet.getString("username")).thenReturn("user1");
        when(resultSet.getString("password")).thenReturn("password1");
        when(resultSet.getInt("roleid")).thenReturn(1);

        // Set ResultSet on PreparedStatement
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Execute the method under test
        User user = userDAO.getUserById(1);

        // Verify the result
        assertNotNull(user);
        assertEquals(1, user.getUserID());
        assertEquals("user1", user.getUsername());
        assertEquals("password1", user.getPassword());
        assertEquals(1, user.getRoleID());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        // Prepare a User instance
        User user = new User(1, "updatedUser", "updatedPassword", 2);

        // Execute the method under test
        userDAO.updateUser(user);

        // Verify that PreparedStatement was called with correct parameters
        verify(preparedStatement).setString(1, user.getUsername());
        verify(preparedStatement).setString(2, user.getPassword());
        verify(preparedStatement).setInt(3, user.getRoleID());
        verify(preparedStatement).setInt(4, user.getUserID());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Execute the method under test
        userDAO.deleteUser(1);

        // Verify that PreparedStatement was called with correct parameter
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).executeUpdate();
    }
}
