package client.gui;

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

        HBox resultHBox = new HBox();
        Label result = new Label();
        result.setMinHeight(100);
        result.setPadding(new Insets(20));
        resultHBox.getChildren().add(result);
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
            result.setText(textField1.getText().trim() + " " + textField2.getText().trim());
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
