package client.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


class SnakePage {

    static Pane build(Stage stage) {

        //Making the buttons
        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setFont(new Font(14));

        Button grid1 = new Button();
        grid1.setMinSize(30, 30);
        grid1.setStyle("-fx-background-color: green;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 1px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
        Button grid2 = new Button();
        grid2.setStyle("-fx-background-color: lightgrey;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 1px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
        grid2.setMinSize(30, 30);

        //Making the layouts
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(returnButton);
        topHBox.setPadding(new Insets(5));
        topHBox.setAlignment(Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        grid.setMinSize(500, 500);
        grid.add(grid1, 0, 0);
        grid.add(grid2, 1, 0);
        grid.setAlignment(Pos.CENTER);

        GridPane buttons = new GridPane();

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setLeft(buttons);
        border.setTop(topHBox);

        //Setting the button actions
        returnButton.setOnAction(event -> {
            HomePage.set(stage, HomePage.build(stage));
        });

        Platform.runLater(grid :: requestFocus);

        return border;
    }

    static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Snake");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);
    }
}
