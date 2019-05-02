package client.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

class GameOverScreen {

    static void show() {

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
            SnakePage.returnButton.fire();
        });

        againButton.setOnAction(event -> {
            stage.close();
            SnakePage.setUpGame();
        });

        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                againButton.fire();
            }
        });

        stage.showAndWait();
    }
}
