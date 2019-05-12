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
            Position position = head.to(direction);
            if (position.isOutOfBounds()) {
                directionsToRemove.add(direction);
            }
            else if (position.status().equals("snake") && !position.isTail()) {
                directionsToRemove.add(direction);
            }
        }
        directions.removeAll(directionsToRemove);


        for (Direction direction : directions) {
            if (isCloser(head.to(direction))) {
                return direction;
            }
        }

        return directions.get(0);
    }

    private boolean isCloser(Position position) {
        int deltaX = Math.abs((head.getX() - apple.getX())) - Math.abs((position.getX() - apple.getX()));
        int deltaY = Math.abs((head.getY() - apple.getY())) - Math.abs((position.getY() - apple.getY()));

        return  (deltaX == 1 || deltaY == 1);
    }
}
