package client.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrimesPage {

    public static void show(Stage stage) {

        Button calcButton = new Button();
        calcButton.setText("Calculate");
        calcButton.setPrefSize(150, 30);
        calcButton.setFont(new Font(20));

        calcButton.setOnAction(event -> {
            stage.close();
            HomePage.show(stage);
        });

        GridPane grid = new GridPane();
        grid.add(calcButton, 0, 0);
        grid.setAlignment(Pos.CENTER);

        Scene scene = new Scene(grid, 800, 400);

        stage.setScene(scene);
        stage.setTitle("Primes");
        stage.show();
    }
}
