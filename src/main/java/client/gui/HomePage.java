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

        Sizes.list.add(HomePage.build(stage));
        Sizes.list.add(PrimesPage.build(stage));
        Sizes.setSizes();

        stage.setHeight(Sizes.STAGE_HEIGHT);
        stage.setWidth(Sizes.STAGE_WIDTH);

        set(stage, build(stage));

        stage.show();
    }

    public static Pane build(Stage stage) {

        //Making the buttons
        Button primeButton = new Button();
        primeButton.setText("Primes");
        primeButton.setPrefSize(100, 50);
        primeButton.setFont(new Font(20));

        Button snakeButton = new Button();
        snakeButton.setText("Snake");
        snakeButton.setPrefSize(100, 50);
        snakeButton.setFont(new Font(20));

        //Making the layouts
        GridPane grid = new GridPane();
        grid.add(primeButton, 0, 0);
        grid.add(snakeButton, 1, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(40);

        //Setting the button actions
        primeButton.setOnAction(event -> {
            PrimesPage.set(stage, PrimesPage.build(stage));
        });

        return grid;
    }

    public static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Homepage");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);
    }
}
