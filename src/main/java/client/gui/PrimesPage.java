package client.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PrimesPage {

    public static void show(Stage stage) {

        //Making the buttons
        Button calcButton = new Button();
        calcButton.setText("Calculate");
        calcButton.setPrefSize(150, 30);
        calcButton.setFont(new Font(20));

        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setPrefSize(50, 25);
        returnButton.setFont(new Font(14));

        //Setting the button actions
        returnButton.setOnAction(event -> {
            //stage.close();
            HomePage.show(stage);
        });

        //Making the layouts
        GridPane grid = new GridPane();
        grid.add(calcButton, 0, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(600, 300);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(returnButton);
        hBox.setPadding(new Insets(5));
        hBox.setAlignment(Pos.CENTER_LEFT);

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setTop(hBox);

        //Setting scene and stage
        Scene scene = new Scene(border);

        stage.setScene(scene);
        stage.setTitle("Primes");
        stage.setMinHeight(Sizes.STAGE_HEIGHT);
        stage.setMinWidth(Sizes.STAGE_WIDTH);
        stage.show();
    }
}
