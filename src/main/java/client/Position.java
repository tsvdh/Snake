package client;

public class Position {

    private int x;
    private int y;

    public Position() {}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position up() {
        return new Position(this.x, this.y - 1);
    }

    public Position down() {
        return new Position(this.x, this.y + 1);
    }

    public Position left() {
        return new Position(this.x - 1, this.y);
    }

    public Position right() {
        return new Position(this.x + 1, this.y);
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
}
