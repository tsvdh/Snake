package client.gui;

import javafx.scene.layout.Pane;

import java.util.LinkedList;

public class Sizes {

    public static int STAGE_HEIGHT = Integer.MAX_VALUE;
    public static int STAGE_WIDTH = Integer.MAX_VALUE;

    public static LinkedList<Pane> list = new LinkedList<>();

    public static void setSizes() {
        for (Pane pane : list) {
            if (pane.getHeight() < STAGE_HEIGHT) {
                STAGE_HEIGHT = (int) pane.getHeight();
            }
            if (pane.getWidth() < STAGE_WIDTH) {
                STAGE_WIDTH = (int) pane.getWidth();
            }
        }
    }
}
