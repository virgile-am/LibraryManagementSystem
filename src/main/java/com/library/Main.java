package src.main.java.com.library;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        // Start JavaFX application on the JavaFX Application Thread
        Platform






                .startup(() -> {
            Stage primaryStage = new Stage();

            // Create a label with the text "Hello, World!"
            Label label = new Label("Hello, World!");

            // Create a scene with the label as the root node
            Scene scene = new Scene(label, 300, 200);

            // Set the scene to the stage and show the stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX Hello World");
            primaryStage.show();
        });
    }
}
