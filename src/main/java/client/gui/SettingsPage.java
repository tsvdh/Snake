package client.gui;

import client.Difficulty;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingsPage {

    static Pane build(Stage stage) {

        //Making the buttons and other clickable objects
        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setFont(new Font(14));

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("easy",
                                            "normal",
                                            "hard");
        choiceBox.setStyle("-fx-font-size: 15");
        choiceBox.setValue(Difficulty.getDifficulty());

        choiceBox.setOnAction(event -> {
            String newDifficulty = choiceBox.getValue();

            if (!Difficulty.getDifficulty().equals(newDifficulty)) {
                Difficulty.setDifficulty(newDifficulty);
            }
        });

        Label label = new Label();
        label.setText("Difficulty of Snake: ");
        label.setFont(new Font(17));

        //Making the layouts
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.add(label, 0, 0);
        grid.add(choiceBox, 1, 0);

        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(returnButton);
        topHBox.setAlignment(Pos.CENTER_LEFT);
        topHBox.setPadding(new Insets(5));

        BorderPane border = new BorderPane();
        border.setTop(topHBox);
        border.setCenter(grid);

        //Setting the button and other clickable object actions
        returnButton.setOnAction(event -> {
            HomePage.set(stage, HomePage.build(stage));
        });

        Platform.runLater(border :: requestFocus);

        return border;
    }

    static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Settings");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);
    }
}
