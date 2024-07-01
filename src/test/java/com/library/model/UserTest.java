package com.library.model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Initialize a User instance with sample data
        user = new User(1, "johndoe", "password123", 2);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, user.getUserID());
        assertEquals("johndoe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(2, user.getRoleID());

        // Test setters
        user.setUserID(2);
        user.setUsername("janedoe");
        user.setPassword("newpassword456");
        user.setRoleID(3);

        assertEquals(2, user.getUserID());
        assertEquals("janedoe", user.getUsername());
        assertEquals("newpassword456", user.getPassword());
        assertEquals(3, user.getRoleID());
    }

    @Test
    public void testPropertyMethods() {
        // Test userID property
        IntegerProperty userIDProperty = user.userIDProperty();
        userIDProperty.set(10);
        assertEquals(10, user.getUserID());

        // Test username property
        StringProperty usernameProperty = user.usernameProperty();
        usernameProperty.set("newusername");
        assertEquals("newusername", user.getUsername());

        // Test password property
        StringProperty passwordProperty = user.passwordProperty();
        passwordProperty.set("newpassword");
        assertEquals("newpassword", user.getPassword());

        // Test roleID property
        IntegerProperty roleIDProperty = user.roleIDProperty();
        roleIDProperty.set(5);
        assertEquals(5, user.getRoleID());
    }

    @Test
    public void testToString() {
        assertEquals("johndoe", user.toString());
    }
}
