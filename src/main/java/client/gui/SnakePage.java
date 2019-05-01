package client.gui;

import client.Difficulty;
import client.Score;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SnakePage {

    static GridElement[][] gridArray = new GridElement[20][20];
    static LinkedList<GridElement> snakeList = new LinkedList<>();

    private static String direction;
    private static boolean directionSet;
    private static String nextDirection;
    private static ScheduledExecutorService scheduler;

    private static Label currentScore;
    private static Label highScore;
    final private static String scoreText = "Current score: ";
    final private static String highScoreText = "High score: ";

    private static Button returnButton;

    private static AI_v1 ai;


    static Pane build(Stage stage) {

        //Making the buttons
        returnButton = new Button();
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
        buttons.setPadding(new Insets(100, 0, 100 , 0));
        buttons.add(upButton, 1, 0);
        buttons.add(downButton, 1, 2);
        buttons.add(leftButton, 0, 1);
        buttons.add(rightButton, 2, 1);
        buttons.setAlignment(Pos.CENTER);

        currentScore = new Label();
        currentScore.setFont(new Font(17));
        currentScore.setText(scoreText + "-");
        highScore = new Label();
        highScore.setFont(new Font(17));
        highScore.setText(highScoreText + "-");

        Text text = new Text();
        text.setFont(new Font(17));
        text.setText("Difficulty: ");
        Text difficulty = new Text();
        difficulty.setFont(Font.font(Font.getDefault().toString(), FontWeight.BOLD, 20));
        difficulty.setText(Difficulty.getDifficulty());

        StackPane alignmentBox = new StackPane();
        alignmentBox.getChildren().add(difficulty);
        alignmentBox.setPadding(new Insets(0, 0, 3, 0));

        HBox difficultyHBox = new HBox();
        difficultyHBox.getChildren().addAll(text,
                                            alignmentBox);
        difficultyHBox.setAlignment(Pos.CENTER);

        VBox leftVBox = new VBox();
        leftVBox.getChildren().addAll(currentScore,
                                    highScore,
                                    buttons,
                                    difficultyHBox);
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setPadding(new Insets(0, 0, 0, 50));

        BorderPane border = new BorderPane();
        border.setCenter(grid);
        border.setLeft(leftVBox);
        border.setTop(topHBox);

        //Setting the button actions
        returnButton.setOnAction(event -> {
            scheduler.shutdown();
            HomePage.set(stage, HomePage.build(stage));
        });

        upButton.setOnAction(event -> setDirection("up"));
        downButton.setOnAction(event -> setDirection("down"));
        leftButton.setOnAction(event -> setDirection("left"));
        rightButton.setOnAction(event -> setDirection("right"));

        Platform.runLater(grid :: requestFocus);

        return border;
    }

    private static void buildGrid(GridPane grid) {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (grid != null) {
                    gridArray[y][x] = new GridElement(x, y);
                    grid.add(gridArray[y][x], x, y);
                }
                gridArray[y][x].setEmpty();
            }
        }
    }

    static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Snake");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);

        scene.setOnKeyPressed(event -> {
            scene.setCursor(Cursor.NONE);

            if (event.getCode().isArrowKey()) {
                setDirection(event.getCode().toString().toLowerCase());
            }
        });

        scene.setOnMouseMoved(event -> {
            scene.setCursor(Cursor.DEFAULT);
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

        boolean playing = false;

        try {
            switch (gridArray[y][x].STATUS) {
                case "apple":
                    addHead(x, y);
                    spawnApple();
                    playing = true;
                    break;
                case "snake":
                    throw new Exception();
                case "empty":
                    addHead(x, y);
                    removeTail();
                    playing = true;
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            int score = snakeList.size();
            if (Score.getScore() < score) {
                Score.setScore(score);
            }
            scheduler.shutdown();

            Platform.runLater(SnakePage :: gameOverScreen);
        }

        if (playing) {
            Platform.runLater(() -> currentScore.setText(scoreText + snakeList.size()));
        }

        directionSet = false;
        if (nextDirection != null) {
            direction = nextDirection;
            nextDirection = null;
        }

        setDirection(ai.nextDirection());
    }

    private static void addHead(int x, int y) {
        snakeList.getFirst().setSnake();
        snakeList.addFirst(gridArray[y][x]);
        gridArray[y][x].setHead();
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
        directionSet = false;
        nextDirection = null;
        currentScore.setText(scoreText + 0);
        highScore.setText(highScoreText + Score.getScore());

        buildGrid(null);

        Random randomGenerator = new Random();
        int x = randomGenerator.nextInt(20);
        int y = randomGenerator.nextInt(20);
        gridArray[y][x].setHead();
        snakeList.addFirst(gridArray[y][x]);
        spawnApple();

        scheduler = Executors.newSingleThreadScheduledExecutor();

        String difficulty = Difficulty.getDifficulty();
        int period;

        switch (difficulty) {
            case "easy":
                period = 300;
                break;
            case "normal":
                period = 200;
                break;
            case "hard":
                period = 100;
                break;
            default:
                period = -1;
        }

        ai = new AI_v1();

        scheduler.scheduleAtFixedRate(new UpdateThread(), 0, period, TimeUnit.MILLISECONDS);
    }

    private static void setDirection(String direction) {
        if (!directionSet) {
            SnakePage.direction = direction;
            directionSet = true;
        } else {
            SnakePage.nextDirection = direction;
        }
    }

    private static void gameOverScreen() {

        //Making the buttons
        Button againButton = new Button();
        againButton.setText("Play again!");
        againButton.setPrefSize(120, 20);
        againButton.setFont(new Font(17));
        Button quitButton = new Button();
        quitButton.setText("Quit");
        quitButton.setFont(new Font(17));
        quitButton.setPrefSize(120, 20);

        //Making the layouts
        HBox hBox = new HBox();
        hBox.getChildren().addAll(againButton,
                                quitButton);
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20, 0, 0, 0));

        Label messageLabel = new Label();
        messageLabel.setText("Game over!");
        messageLabel.setFont(new Font(25));

        Label optionLabel = new Label();
        optionLabel.setText("Press enter to play again");
        optionLabel.setFont(new Font(14));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(messageLabel,
                                hBox,
                                optionLabel);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(20));

        Platform.runLater(vBox :: requestFocus);

        //Setting scene and stage
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        //Setting the button actions
        quitButton.setOnAction(event -> {
            stage.close();
            returnButton.fire();
        });

        againButton.setOnAction(event -> {
            stage.close();
            setUpGame();
        });

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                againButton.fire();
            }
        });

        stage.showAndWait();
    }
}
