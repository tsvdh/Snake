package client.gui;

import javafx.concurrent.Task;

class UpdateTask extends Task<Void> {

    @Override
    protected Void call() {
        System.out.println("test");
        SnakePage.move();
        return null;
    }
}
