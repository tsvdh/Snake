package client.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

public class Sizes {

    public static int STAGE_HEIGHT = Integer.MIN_VALUE;
    public static int STAGE_WIDTH = Integer.MIN_VALUE;

    public static LinkedList<Scene> list = new LinkedList<>();

    public static void setSizes() {
        for (Scene scene: list) {
            if (scene.getHeight() > STAGE_HEIGHT) {
                STAGE_HEIGHT = (int) scene.getHeight();
            }
            if (scene.getWidth() > STAGE_WIDTH) {
                STAGE_WIDTH = (int) scene.getWidth();
            }
        }
    }
}
