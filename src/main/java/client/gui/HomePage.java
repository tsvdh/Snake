package client.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomePage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        set(stage, build(stage));

        Sizes.list.add(HomePage.build(stage));
        Sizes.list.add(PrimesPage.build(stage));
        Sizes.setSizes();

        stage.setMinHeight(Sizes.STAGE_HEIGHT);
        stage.setMinWidth(Sizes.STAGE_WIDTH);
        stage.show();
    }

    public static Scene build(Stage stage) {

        //Making the buttons
        Button primeButton = new Button();
        primeButton.setText("Primes");
        primeButton.setPrefSize(100, 50);
        primeButton.setFont(new Font(20));

        Button snakeButton = new Button();
        snakeButton.setText("Snake");
        snakeButton.setPrefSize(100, 50);
        snakeButton.setFont(new Font(20));

        //Setting the button actions
        primeButton.setOnAction(event -> {
            PrimesPage.set(stage, PrimesPage.build(stage));
        });

        //Making the layouts
        GridPane grid = new GridPane();
        grid.add(primeButton, 0, 0);
        grid.add(snakeButton, 1, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(40);

        return new Scene(grid);
    }

    public static void set(Stage stage, Scene scene) {

        //Setting stage
        stage.setTitle("Homepage");
        stage.setScene(scene);
    }
}