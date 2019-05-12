package client.gui;

class Position {

    private int x;
    private int y;

    Position() {}

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

     void setY(int y) {
        this.y = y;
    }

    private Position up() {
        return new Position(this.x, this.y - 1);
    }

    private Position down() {
        return new Position(this.x, this.y + 1);
    }

    private Position left() {
        return new Position(this.x - 1, this.y);
    }

    private Position right() {
        return new Position(this.x + 1, this.y);
    }

    Position to(Direction direction) {
        switch (direction) {
            case UP:
                return up();
            case DOWN:
                return down();
            case LEFT:
                return left();
            case RIGHT:
                return right();
            default:
                return null;
        }
    }

    String status() {
        return SnakePage.gridArray[this.y][this.x].STATUS;
    }

    boolean isTail() {
        int size = SnakePage.snakeList.size();
        int index = SnakePage.snakeList.indexOf(SnakePage.gridArray[this.y][this.x]);

        return ((index == size - 1) && (size > 2));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Position) {
            Position that = (Position) other;
            return (this.x == that.x && this.y == that.y);
        }
        return false;
    }

    private boolean isOutOfBounds(int coordinate) {
        return coordinate > 19 || coordinate < 0;
    }

    boolean isOutOfBounds() {
        return (isOutOfBounds(x) || isOutOfBounds(y));

    }
}
