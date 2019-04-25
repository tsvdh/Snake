package client.gui;

class UpdateThread implements Runnable {

    @Override
    public void run() {
        SnakePage.move();
    }
}
