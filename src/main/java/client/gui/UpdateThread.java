package client.gui;

class UpdateThread implements Runnable {

    @Override
    public void run() {
        System.out.println("test");
        SnakePage.move();
    }
}
