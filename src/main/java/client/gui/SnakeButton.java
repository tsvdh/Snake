package client.gui;

import javafx.scene.control.Button;

class SnakeButton extends Button {

    String STATUS;

    SnakeButton() {
        super();
        this.setMinSize(30, 30);
    }

    void setEmpty() {
        this.STATUS = "empty";
        this.setStyle("-fx-background-color: lightgrey;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 0.5px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
    }

    void setSnake() {
        this.STATUS = "snake";
        this.setStyle("-fx-background-color: green;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 0.5px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
    }

    void setApple() {
        this.STATUS = "apple";
        this.setStyle("-fx-background-color: red;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 0.5px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
    }

    void setHead() {
        this.STATUS = "head";
        this.setStyle("-fx-background-color: green;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 0.5px;"
                + "-fx-border-color: black;"
                + "-fx-border-radius: 0px");
    }
}
