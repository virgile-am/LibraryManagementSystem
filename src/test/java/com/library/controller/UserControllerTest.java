package com.library.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.library.dao.PatronDAO;
import com.library.dao.UserDAO;
import com.library.model.Patron;
import com.library.model.User;

import java.util.Arrays;
import java.util.Collections;

@ExtendWith(ApplicationExtension.class)
class UserControllerTest {

    @Mock
    private UserDAO mockUserDAO;

    @Mock
    private PatronDAO mockPatronDAO;

    @InjectMocks
    private UserController userController;

    private Stage stage;

    @Start
    private void start(Stage stage) throws Exception {
        this.stage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/views/user.fxml"));
        Scene scene = new Scene(loader.load());
        userController = loader.getController();
        userController.userDAO = mockUserDAO;
        userController.patronDAO = mockPatronDAO;
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInitialize(FxRobot robot) {
        User user1 = new User(1, "user1", "pass1", 1);
        User user2 = new User(2, "user2", "pass2", 2);
        when(mockUserDAO.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        Patron patron1 = new Patron(1, "Patron1", "patron1@example.com", "1234567890", "Address1");
        Patron patron2 = new Patron(2, "Patron2", "patron2@example.com", "0987654321", "Address2");
        when(mockPatronDAO.getAllPatrons()).thenReturn(Arrays.asList(patron1, patron2));

        userController.initialize();

        assertEquals(2, userController.userTable.getItems().size());
        assertEquals("user1", userController.userTable.getItems().get(0).getUsername());
        assertEquals("user2", userController.userTable.getItems().get(1).getUsername());

        assertEquals(2, userController.patronTable.getItems().size());
        assertEquals("Patron1", userController.patronTable.getItems().get(0).getName());
        assertEquals("Patron2", userController.patronTable.getItems().get(1).getName());
    }

    @Test
    void testHandleAddUser(FxRobot robot) {
        when(mockUserDAO.getAllUsers()).thenReturn(Collections.emptyList());

        robot.clickOn("#userIdField").write("1");
        robot.clickOn("#usernameField").write("newUser");
        robot.clickOn("#passwordField").write("newPass");
        robot.clickOn("#roleIdField").write("2");
        robot.clickOn("#addUserButton");

        verify(mockUserDAO).addUser(any(User.class));
        verify(mockUserDAO, times(2)).getAllUsers();
    }

    @Test
    void testHandleUpdateUser(FxRobot robot) {
        User user = new User(1, "oldUser", "oldPass", 1);
        when(mockUserDAO.getAllUsers()).thenReturn(Collections.singletonList(user));

        userController.initialize();

        robot.clickOn("#userTable").clickOn("oldUser"); // Select the user row
        robot.clickOn("#usernameField").eraseText(7).write("newUser");
        robot.clickOn("#passwordField").eraseText(7).write("newPass");
        robot.clickOn("#roleIdField").eraseText(1).write("2");
        robot.clickOn("#updateUserButton");

        verify(mockUserDAO).updateUser(any(User.class));
        verify(mockUserDAO, times(2)).getAllUsers();
    }

    @Test
    void testHandleDeleteUser(FxRobot robot) {
        User user = new User(1, "user", "pass", 1);
        when(mockUserDAO.getAllUsers()).thenReturn(Collections.singletonList(user)).thenReturn(Collections.emptyList());

        userController.initialize();

        robot.clickOn("#userTable").clickOn("user"); // Select the user row
        robot.clickOn("#deleteUserButton");

        verify(mockUserDAO).deleteUser(1);
        verify(mockUserDAO, times(2)).getAllUsers();
        assertTrue(userController.userTable.getItems().isEmpty());
    }

    @Test
    void testHandleAddPatron(FxRobot robot) {
        when(mockPatronDAO.getAllPatrons()).thenReturn(Collections.emptyList());

        robot.clickOn("#patronIdField").write("1");
        robot.clickOn("#nameField").write("New Patron");
        robot.clickOn("#emailField").write("new@example.com");
        robot.clickOn("#phoneField").write("1234567890");
        robot.clickOn("#addressField").write("New Address");
        robot.clickOn("#addPatronButton");

        verify(mockPatronDAO).addPatron(any(Patron.class));
        verify(mockPatronDAO, times(2)).getAllPatrons();
    }

    @Test
    void testHandleUpdatePatron(FxRobot robot) {
        Patron patron = new Patron(1, "Old Patron", "old@example.com", "0987654321", "Old Address");
        when(mockPatronDAO.getAllPatrons()).thenReturn(Collections.singletonList(patron));

        userController.initialize();

        robot.clickOn("#patronTable").clickOn("Old Patron"); // Select the patron row
        robot.clickOn("#nameField").eraseText(10).write("New Patron");
        robot.clickOn("#emailField").eraseText(15).write("new@example.com");
        robot.clickOn("#phoneField").eraseText(10).write("1234567890");
        robot.clickOn("#addressField").eraseText(11).write("New Address");
        robot.clickOn("#updatePatronButton");

        verify(mockPatronDAO).updatePatron(any(Patron.class));
        verify(mockPatronDAO, times(2)).getAllPatrons();
    }

    @Test
    void testHandleDeletePatron(FxRobot robot) {
        Patron patron = new Patron(1, "Patron", "patron@example.com", "1234567890", "Address");
        when(mockPatronDAO.getAllPatrons()).thenReturn(Collections.singletonList(patron)).thenReturn(Collections.emptyList());

        userController.initialize();

        robot.clickOn("#patronTable").clickOn("Patron"); // Select the patron row
        robot.clickOn("#deletePatronButton");

        verify(mockPatronDAO).deletePatron(1);
        verify(mockPatronDAO, times(2)).getAllPatrons();
        assertTrue(userController.patronTable.getItems().isEmpty());
    }
}
