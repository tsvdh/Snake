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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class SnakePage {

    static GridElement[][] gridArray = new GridElement[20][20];
    static LinkedList<GridElement> snakeList = new LinkedList<>();

    private static Direction direction;
    private static boolean directionSet;
    private static Direction nextDirection;
    private static ScheduledExecutorService scheduler;

    private static Label currentScore;
    private static Label highScore;
    final private static String scoreText = "Current score: ";
    final private static String highScoreText = "High score: ";

    static Button returnButton;

    private static Smart_Aggressive_AI ai;


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

        upButton.setOnAction(event -> setDirection(Direction.UP));
        downButton.setOnAction(event -> setDirection(Direction.DOWN));
        leftButton.setOnAction(event -> setDirection(Direction.LEFT));
        rightButton.setOnAction(event -> setDirection(Direction.RIGHT));

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
                String direction = event.getCode().toString().toUpperCase();
                setDirection(Direction.valueOf(direction));
            }
        });

        scene.setOnMouseMoved(event -> {
            scene.setCursor(Cursor.DEFAULT);
        });

        stage.setOnCloseRequest(event -> {
            scheduler.shutdown();
        });

        //Starting the game
        setUpGame();
    }

    static void move() {
        int x = snakeList.getFirst().X;
        int y = snakeList.getFirst().Y;

        if (!(direction == null)) {
            x += direction.getDeltaX();
            y += direction.getDeltaY();
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
                    if (snakeList.indexOf(gridArray[y][x]) != snakeList.size() - 1) {
                        throw new IndexOutOfBoundsException();
                    }
                    else if (snakeList.size() == 2) {
                        throw new IndexOutOfBoundsException();
                    }
                case "empty":
                    removeTail();
                    addHead(x, y);
                    playing = true;
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException e) {
            int score = snakeList.size();
            if (Score.getScore() < score) {
                Score.setScore(score);
            }
            scheduler.shutdown();

            Platform.runLater(GameOverScreen :: show);
        }

        if (playing) {
            Platform.runLater(() -> currentScore.setText(scoreText + snakeList.size()));
        }

        directionSet = false;
        if (nextDirection != null) {
            direction = nextDirection;
            nextDirection = null;
        }

        Direction aiDirection = ai.nextDirection();
        System.out.println(aiDirection);
        direction = aiDirection;

//        direction = ai.nextDirection();
    }

    private static void addHead(int x, int y) {
        snakeList.addFirst(gridArray[y][x]);
        gridArray[y][x].setHead();
        if (snakeList.size() > 1) {
            snakeList.get(1).setSnake();
        }
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

    static void setUpGame() {
        direction = null;
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

        ai = new Smart_Aggressive_AI();

        scheduler.scheduleAtFixedRate(new UpdateThread(), 0, period, TimeUnit.MILLISECONDS);
    }

    private static void setDirection(Direction direction) {
        if (!directionSet) {
            SnakePage.direction = direction;
            directionSet = true;
        } else {
            SnakePage.nextDirection = direction;
        }
    }
}
