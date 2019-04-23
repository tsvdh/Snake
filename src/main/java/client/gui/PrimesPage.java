package client.gui;

import client.PrimeTask;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class PrimesPage {

    static Pane build(Stage stage) {

        //Making the buttons
        Button calcButton = new Button();
        calcButton.setText("Calculate");
        calcButton.setPrefSize(150, 30);
        calcButton.setFont(new Font(20));

        Button returnButton = new Button();
        returnButton.setText("back");
        returnButton.setPrefSize(50, 25);
        returnButton.setFont(new Font(14));

        Button stopButton = new Button();
        stopButton.setText("Stop");
        stopButton.setPrefSize(60, 30);
        stopButton.setFont(new Font(15));
        stopButton.setVisible(false);

        //Making the layouts
        HBox inputHBox = new HBox();
        Text text1 = new Text("Calculate primes from");
        text1.setFont(new Font(15));
        Text text2 = new Text("to");
        text2.setFont(new Font(15));
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();
        inputHBox.getChildren().addAll( text1,
                                        textField1,
                                        text2,
                                        textField2,
                                        calcButton);
        inputHBox.setPadding(new Insets(10));
        inputHBox.setSpacing(5);
        inputHBox.setAlignment(Pos.CENTER);

        Label result = new Label();
        result.setMinHeight(100);
        result.setPadding(new Insets(20));
        result.setFont(new Font(14));

        HBox resultHBox = new HBox();
        resultHBox.getChildren().addAll(result,
                                        stopButton);
        resultHBox.setAlignment(Pos.CENTER);

        VBox centerVBox = new VBox();
        centerVBox.setSpacing(10);
        centerVBox.getChildren().addAll(inputHBox,
                                        resultHBox);
        centerVBox.setAlignment(Pos.CENTER);

        HBox topHBox = new HBox();
        topHBox.getChildren().addAll(returnButton);
        topHBox.setPadding(new Insets(5));
        topHBox.setAlignment(Pos.CENTER_LEFT);

        BorderPane border = new BorderPane();
        border.setCenter(centerVBox);
        border.setTop(topHBox);

        //Setting the button actions
        returnButton.setOnAction(event -> {
            HomePage.set(stage, HomePage.build(stage));
        });

        calcButton.setOnAction(event -> {
            boolean valid = true;
            String string1 = textField1.getText().trim();
            String string2 = textField2.getText().trim();
            Long number1 = 0L;
            Long number2 = -1L;
            try {
                number1 = new Long(string1);
                number2 = new Long(string2);
            } catch (NumberFormatException e) {
                valid = false;
            }
            if (valid) {
                if (number1 <= number2) {
                    border.requestFocus();
                    calcButton.setDisable(true);
                    stopButton.setVisible(true);

                    PrimeTask task = new PrimeTask(number1, number2);

                    result.textProperty().bind(task.messageProperty());

                    double total = number2 - number1 + 1;
                    task.setOnSucceeded(succeededEvent -> {
                        stopButton.setVisible(false);
                        result.textProperty().unbind();

                        DecimalFormat decimalFormat = new DecimalFormat("#.####");
                        decimalFormat.setRoundingMode(RoundingMode.CEILING);
                        double percentage = task.getValue().size() / total * 100;
                        percentage = new Double(decimalFormat.format(percentage));

                        result.setText(percentage + "%" + " are primes.");
                        calcButton.setDisable(false);
                    });

                    returnButton.setOnAction(returnAction -> {
                        task.cancel(true);
                        HomePage.set(stage, HomePage.build(stage));
                    });

                    stopButton.setOnAction(stopAction -> {
                        task.cancel(true);
                        stopButton.setVisible(false);
                        result.textProperty().unbind();
                        result.setText("Stopped");
                        calcButton.setDisable(false);
                    });

                    ExecutorService executorService = Executors.newSingleThreadExecutor();
                    executorService.execute(task);
                } else {
                    result.setText("The first number must be "
                            + "greater or equal than the second number.");
                }
            } else {
                result.setText("Please enter numbers.");
            }
        });

        return border;
    }

    static void set(Stage stage, Pane pane) {

        //Setting scene and stage
        stage.setTitle("Primes");
        Scene scene = new Scene(pane, Sizes.STAGE_WIDTH, Sizes.STAGE_HEIGHT);
        stage.setScene(scene);
    }
}
