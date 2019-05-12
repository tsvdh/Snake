package client.gui;

import java.util.ArrayList;
import java.util.Arrays;

class Smart_Aggressive_AI extends AI{

    Direction nextDirection() {
        head = getHead();
        apple = findApple();

        ArrayList<Direction> directions = new ArrayList<>(Arrays.asList(Direction.values()));
        ArrayList<Direction> directionsToRemove = new ArrayList<>();


        for (Direction direction : directions) {
            Position position = head.go(direction);
            if (position.isOutOfBounds()) {
                directionsToRemove.add(direction);
            }
            else if (position.getStatus().equals("snake") && !position.isTail()) {
                directionsToRemove.add(direction);
            }
        }
        directions.removeAll(directionsToRemove);


        for (Direction direction : directions) {
            if (isCloser(head.go(direction))) {
                return direction;
            }
        }
        

        if (!directions.isEmpty()) {
            return directions.get(0);
        } else {
            return Direction.UP;
        }
    }

    private boolean isCloser(Position position) {
        int deltaX = Math.abs((head.getX() - apple.getX())) - Math.abs((position.getX() - apple.getX()));
        int deltaY = Math.abs((head.getY() - apple.getY())) - Math.abs((position.getY() - apple.getY()));

        return  (deltaX == 1 || deltaY == 1);
    }
}
