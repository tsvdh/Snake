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

import java.util.LinkedList;

class SnakePage {

    private static GridElement[][] gridArray = new GridElement[20][20];
    private static LinkedList<GridElement> snakeList = new LinkedList<>();

    static Pane build(Stage stage) {

        //Making the buttons
        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setFont(new Font(14));

        Button upButton = new Button();
        upButton.setPrefSize(50, 50);
        upButton.setStyle("-fx-background-image: url('/images/icons8-up-filled-50.png')");

        Button downButton = new Button();
        downButton.setPrefSize(50, 50);
        downButton.setStyle("-fx-background-image: url('/images/icons8-down-filled-50.png');");

        Button leftButton = new Button();
        leftButton.setPrefSize(50, 50);
        leftButton.setStyle("-fx-background-image: url('/images/icons8-left-filled-50.png')");

        Button rightButton = new Button();
        rightButton.setPrefSize(50, 50);
        rightButton.setStyle("-fx-background-image: url('/images/icons8-right-filled-50.png')");

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
        snakeList.addLast(gridArray[4][3]);
        snakeList.addLast(gridArray[3][3]);
        snakeList.addLast(gridArray[3][2]);
        snakeList.addLast(gridArray[3][1]);

        GridPane buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(0, 0, 0 , 50));
        buttons.add(upButton, 1, 0);
        buttons.add(downButton, 1, 2);
        buttons.add(leftButton, 0, 1);
        buttons.add(rightButton, 2, 1);

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setLeft(buttons);
        border.setTop(topHBox);

        //Setting the button actions
        returnButton.setOnAction(event -> {
            snakeList.clear();
            HomePage.set(stage, HomePage.build(stage));
        });

        upButton.setOnAction(event -> move("up"));

        downButton.setOnAction(event -> move("down"));

        leftButton.setOnAction(event -> move("left"));

        rightButton.setOnAction(event -> move("right"));

        Platform.runLater(grid :: requestFocus);

        return border;
    }

    private static void buildGrid(GridPane grid) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                gridArray[y][x] = new GridElement(x, y);
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

    private static void move(String direction) {
        int x = snakeList.getFirst().X;
        int y = snakeList.getFirst().Y;

        switch (direction) {
            case "up":
                y--;
                break;
            case "down":
                y++;
                break;
            case "left":
                x--;
                break;
            case "right":
                x++;
                break;
            default:
                break;
        }

        snakeList.getFirst().setSnake();
        gridArray[y][x].setHead();
        snakeList.addFirst(gridArray[y][x]);

        snakeList.getLast().setEmpty();
        snakeList.removeLast();
    }
}
