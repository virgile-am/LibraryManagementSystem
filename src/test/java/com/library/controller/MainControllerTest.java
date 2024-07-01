package com.library.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class MainControllerTest {

    private MainController mainController;
    private BorderPane rootPane;

    @Start
    private void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafx/views/main.fxml"));
        rootPane = loader.load();
        mainController = loader.getController();

        Scene scene = new Scene(rootPane);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setUp() {
        assertNotNull(mainController, "MainController should be initialized");
    }

    @Test
    void testHandleShowBooks(FxRobot robot) {
        robot.clickOn("#showBooksButton");
        assertNotNull(rootPane.getCenter());
        assertTrue(rootPane.getCenter().getId().equals("bookView"), "Center should be bookView");
    }

    @Test
    void testHandleShowTransactions(FxRobot robot) {
        robot.clickOn("#showTransactionsButton");
        assertNotNull(rootPane.getCenter());
        assertTrue(rootPane.getCenter().getId().equals("transactionView"), "Center should be transactionView");
    }

    @Test
    void testHandleShowUserManagement(FxRobot robot) {
        robot.clickOn("#showUserManagementButton");
        assertNotNull(rootPane.getCenter());
        assertTrue(rootPane.getCenter().getId().equals("userManagementView"), "Center should be userManagementView");
    }
}