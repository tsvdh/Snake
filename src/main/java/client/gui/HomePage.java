package client.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Button exitButton;

    public void start(Stage stage) {


        exitButton = new Button();
        exitButton.setText("Hello World!");

        exitButton.setOnAction((event -> {
            stage.close();
        }));

        StackPane layout = new StackPane();
        layout.getChildren().add(exitButton);

        Scene scene = new Scene(layout, 500, 300);

        stage.setTitle("Homepage");
        stage.setScene(scene);
        stage.show();
    }
}
