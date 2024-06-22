package src.main.resources.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private BorderPane rootPane;

    @FXML
    public void handleShowBooks() {
        loadView("/src/main/resources/javafx/views/book.fxml");
    }

    @FXML
    public void handleShowTransactions() {
        loadView("/src/main/resources/javafx/views/transaction.fxml");
    }

    @FXML
    public void handleShowUserManagement() {
        loadView("/src/main/resources/javafx/views/user_management.fxml");
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
