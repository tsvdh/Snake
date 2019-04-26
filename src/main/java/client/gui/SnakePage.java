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
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SnakePage {

    private static GridElement[][] gridArray = new GridElement[20][20];
    private static LinkedList<GridElement> snakeList = new LinkedList<>();
    private static String direction = "none";
    private static ScheduledExecutorService scheduler;

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
            scheduler.shutdown();
            HomePage.set(stage, HomePage.build(stage));
        });

        upButton.setOnAction(event -> direction = "up");

        downButton.setOnAction(event -> direction = "down");

        leftButton.setOnAction(event -> direction = "left");

        rightButton.setOnAction(event -> direction = "right");

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

        scene.setOnKeyPressed(event -> {
            if (event.getCode().isArrowKey()) {
                direction = event.getCode().toString().toLowerCase();
            }
        });

        //Starting the game
        setUpGame();
    }

    static void move() {
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
        try {
            switch (gridArray[y][x].STATUS) {
                case "apple":
                    addHead(x, y);
                    spawnApple();
                    break;
                case "snake":
                    throw new Exception();
                case "empty":
                    addHead(x, y);
                    removeTail();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Game over");
            scheduler.shutdown();
        }

    }

    private static void addHead(int x, int y) {
        snakeList.getFirst().setSnake();
        gridArray[y][x].setHead();
        snakeList.addFirst(gridArray[y][x]);
    }

    private static void removeTail() {
        snakeList.getLast().setEmpty();
        snakeList.removeLast();
    }

    private static void spawnApple() {
        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(20);
        int y = randomGenerator.nextInt(20);

        if (gridArray[y][x].STATUS.equals("empty")) {
            gridArray[y][x].setApple();
        } else {
            spawnApple();
        }
    }

    private static void setUpGame() {
        direction = "none";
        snakeList.clear();

        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(20);
        int y = randomGenerator.nextInt(20);
        gridArray[y][x].setHead();
        snakeList.addFirst(gridArray[y][x]);
        spawnApple();

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new UpdateThread(), 0, 100, TimeUnit.MILLISECONDS);
    }
}
