package com.library.dao;

import com.library.config.DatabaseConfig;
import com.library.model.UserPrivilege;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPrivilegeDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private UserPrivilegeDAO userPrivilegeDAO;

    @Captor
    private ArgumentCaptor<Integer> integerCaptor;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(DatabaseConfig.getConnection()).thenReturn(mockConnection);
    }

    @Test
    void testAddUserPrivilege() throws SQLException {
        String query = "INSERT INTO user_privilege (userID, permID) VALUES (?, ?)";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);

        UserPrivilege userPrivilege = new UserPrivilege(1, 100);
        userPrivilegeDAO.addUserPrivilege(userPrivilege);

        verify(mockConnection).prepareStatement(query);
        verify(mockPreparedStatement).setInt(1, userPrivilege.getUserID());
        verify(mockPreparedStatement).setInt(2, userPrivilege.getPermID());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    void testGetAllUserPrivileges() throws SQLException {
        String query = "SELECT * FROM user_privilege";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        UserPrivilege userPrivilege1 = new UserPrivilege(1, 100);
        UserPrivilege userPrivilege2 = new UserPrivilege(2, 200);
        when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("userID")).thenReturn(1).thenReturn(2);
        when(mockResultSet.getInt("permID")).thenReturn(100).thenReturn(200);

        List<UserPrivilege> userPrivileges = userPrivilegeDAO.getAllUserPrivileges();

        assertEquals(2, userPrivileges.size());
        assertEquals(userPrivilege1, userPrivileges.get(0));
        assertEquals(userPrivilege2, userPrivileges.get(1));
    }

    @Test
    void testGetUserPrivilegesByUserID() throws SQLException {
        String query = "SELECT * FROM user_privilege WHERE userID = ?";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        UserPrivilege userPrivilege = new UserPrivilege(1, 100);
        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("userID")).thenReturn(1);
        when(mockResultSet.getInt("permID")).thenReturn(100);

        List<UserPrivilege> userPrivileges = userPrivilegeDAO.getUserPrivilegesByUserID(1);

        assertEquals(1, userPrivileges.size());
        assertEquals(userPrivilege, userPrivileges.get(0));
    }

    @Test
    void testDeleteUserPrivilege() throws SQLException {
        String query = "DELETE FROM user_privilege WHERE userID = ? AND permID = ?";
        when(mockConnection.prepareStatement(query)).thenReturn(mockPreparedStatement);

        userPrivilegeDAO.deleteUserPrivilege(1, 100);

        verify(mockConnection).prepareStatement(query);
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 100);
        verify(mockPreparedStatement).executeUpdate();
    }
}
