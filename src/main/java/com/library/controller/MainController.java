package com.library.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private BorderPane rootPane;

    @FXML
    public void handleShowBooks() {
        loadView("/javafx/views/book.fxml");
    }

    @FXML
    public void handleShowTransactions() {
        loadView("/javafx/views/transaction.fxml");
    }

    @FXML
    public void handleShowUserManagement() {
        loadView("/javafx/views/user_management.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Pane view = loader.load();
            rootPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
