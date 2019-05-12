package client.gui;

import java.util.LinkedList;

abstract class AI {

    private GridElement[][] gridArray;
    private LinkedList<GridElement> snakeList;

    Position head;
    Position apple;

    AI() {
        gridArray = SnakePage.gridArray;
        snakeList = SnakePage.snakeList;
    }

    Position findApple() {
        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < 20; x++) {
                if (gridArray[y][x].STATUS.equals("apple")) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    Position getHead() {
        GridElement head = snakeList.getFirst();
        Position position = new Position();

        position.setX(head.X);
        position.setY(head.Y);

        return position;
    }

    abstract Direction nextDirection();
}
