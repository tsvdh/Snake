package client.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }



    public void start(Stage stage) {
        show(stage);

    }

    public static void show(Stage stage) {
        Button primeButton;
        Button snakeButton;

        primeButton = new Button();
        primeButton.setText("Primes");
        primeButton.setPrefSize(100, 50);
        primeButton.setFont(new Font(20));

        snakeButton = new Button();
        snakeButton.setText("Snake");
        snakeButton.setPrefSize(100, 50);
        snakeButton.setFont(new Font(20));

        primeButton.setOnAction(event -> {
            stage.close();
            PrimesPage.show(stage);
        });

        GridPane grid = new GridPane();
        grid.add(primeButton, 0, 0);
        grid.add(snakeButton, 1 ,0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(40);

        Scene scene = new Scene(grid, 500, 300);

        stage.setTitle("Homepage");
        stage.setScene(scene);
        stage.show();
    }
}
