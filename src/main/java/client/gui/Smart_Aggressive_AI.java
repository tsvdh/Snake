package client.gui;

import java.util.ArrayList;
import java.util.Arrays;

public class Smart_Aggressive_AI extends AI{

    Direction nextDirection() {
        head = getHead();
        apple = findApple();

        ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));

        for (Direction direction : directions) {
            Position position = head.to(direction);
            if (position.status().equals("snake") && !position.isTail()) {
                directions.remove(direction);
            }
        }

        for (Direction direction : directions) {

        }

        return null;
    }

    boolean isCloser(Position position) {
        int deltaX = Math.abs((head.getX() - apple.getX())) - Math.abs((position.getX() - apple.getX()));
        int deltaY = Math.abs((head.getY() - apple.getY())) - Math.abs((position.getY() - apple.getY()));

        return  (deltaX == 1 || deltaY == 1);
    }
}
