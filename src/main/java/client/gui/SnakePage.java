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

    private static SnakeButton[][] gridArray = new SnakeButton[20][20];

    static Pane build(Stage stage) {

        //Making the buttons
        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setFont(new Font(14));

        SnakeButton grid1 = new SnakeButton();
        SnakeButton grid2 = new SnakeButton();
        grid1.setApple();

        //Making the layouts
        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(returnButton);
        topHBox.setPadding(new Insets(5));
        topHBox.setAlignment(Pos.CENTER_LEFT);

        GridPane grid = new GridPane();
        buildGrid(grid);
        grid.setPrefSize(grid.getWidth() + 50, grid.getHeight() + 50);
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

    private static void buildGrid(GridPane grid) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                gridArray[y][x] = new SnakeButton();
                grid.add(gridArray[y][x], x, y);
            }
        }
    }

    static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Snake");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);
    }
}
