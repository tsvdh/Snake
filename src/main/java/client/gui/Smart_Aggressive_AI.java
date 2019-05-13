package client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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


        if (directions.size() == 2) {
            if (Direction.areOpposites(directions.get(0), directions.get(1))) {

                if (isClosed(directions.get(0))) {
                    return directions.get(1);
                }
                if (isClosed(directions.get(1))){
                    return directions.get(0);
                }
            }
        }


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

        return (deltaX == 1 || deltaY == 1);
    }

    private boolean isClosed(Direction direction) {

        if (head.go(direction).getStatus().equals("apple")) {
            return false;
        }

        PositionSet colored = new PositionSet();
        Queue<Position> queue = new LinkedList<>();

        colored.add(head.go(direction));
        queue.add(head.go(direction));

        while (!queue.isEmpty()) {

            Position current = queue.remove();
            PositionSet neighbours = current.getNeighbours();
            neighbours.remove("snake");

            for (Position position : neighbours) {
                if (!colored.contains(position)) {
                    colored.add(position);
                    queue.add(position);

                    if (position.getStatus().equals("apple")) {
                        System.out.println("open");
                        return false;
                    }
                }
            }
        }
        System.out.println("closed");
        return true;
    }
}
