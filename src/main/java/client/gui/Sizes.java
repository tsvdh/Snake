package client.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.LinkedList;

class Sizes {

    static double STAGE_HEIGHT = Integer.MIN_VALUE;
    static double STAGE_WIDTH = Integer.MIN_VALUE;

    static LinkedList<Pane> list = new LinkedList<>();


    static void setSizes() {
        for (Pane pane: list) {

            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            double height = stage.getHeight();
            double width = stage.getWidth();

            if (height > STAGE_HEIGHT) {
                STAGE_HEIGHT = height;
            }
            if (width > STAGE_WIDTH) {
                STAGE_WIDTH = width;
            }

            stage.close();
        }
    }
}
