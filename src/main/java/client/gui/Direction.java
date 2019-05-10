package client.gui;

enum  Direction {

    UP(0, -1),

    DOWN(0, 1),

    LEFT(-1, 0),

    RIGHT(1, 0);

    private final int deltaX;

    private final int deltaY;

    /**
     * Makes a direction.
     * @param deltaX The difference in x.
     * @param deltaY The difference in y.
     */
    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Gets the difference in x.
     * @return The difference.
     */
    int getDeltaX() {
        return deltaX;
    }

    /**
     * Gets the difference in y.
     * @return The difference.
     */
    int getDeltaY() {
        return deltaY;
    }
}
