package client.gui;

import client.Position;

import java.util.LinkedList;

class Aggressive_AI {

    private GridElement[][] gridArray;
    private LinkedList<GridElement> snakeList;

    Aggressive_AI() {
        gridArray = SnakePage.gridArray;
        snakeList = SnakePage.snakeList;
    }

    String nextDirection() {
        Position head = getHead();
        Position apple = findApple();

        if (apple == null) {
            return null;
        }

        if (head.getX() != apple.getX()) {
            if (head.getX() > apple.getX()) {
                return "left";
            } else {
                return "right";
            }
        } else {
            if (head.getY() > apple.getY()) {
                return "up";
            } else {
                return "down";
            }
        }
    }

    private Position findApple() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (gridArray[y][x].STATUS.equals("apple")) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    private Position getHead() {
        GridElement head = snakeList.getFirst();
        Position position = new Position();

        position.setX(head.X);
        position.setY(head.Y);

        return position;
    }
}
