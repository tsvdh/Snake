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
        grid.setPadding(new Insets(50));
        grid.setAlignment(Pos.CENTER);
        gridArray[15][10].setApple();
        gridArray[3][1].setSnake();
        gridArray[3][2].setSnake();
        gridArray[3][3].setSnake();
        gridArray[4][3].setHead();

        GridPane buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(0, 0, 0 , 50));

        Button upButton = new Button();
        upButton.setPrefSize(50, 50);
        upButton.setStyle("-fx-background-image: url('/images/icons8-up-filled-50.png')");
        buttons.add(upButton, 1, 0);

        Button downButton = new Button();
        downButton.setPrefSize(50, 50);
        downButton.setStyle("-fx-background-image: url('/images/icons8-down-filled-50.png');");
        buttons.add(downButton, 1, 2);

        Button leftButton = new Button();
        leftButton.setPrefSize(50, 50);
        leftButton.setStyle("-fx-background-image: url('/images/icons8-left-filled-50.png')");
        buttons.add(leftButton, 0, 1);

        Button rightButton = new Button();
        rightButton.setPrefSize(50, 50);
        rightButton.setStyle("-fx-background-image: url('/images/icons8-right-filled-50.png')");
        buttons.add(rightButton, 2, 1);


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
                gridArray[y][x].setEmpty();
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
