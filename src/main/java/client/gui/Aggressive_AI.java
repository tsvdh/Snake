package client.gui;

import client.Position;

class Aggressive_AI extends AI{

    private String lastDirection = "none";

    @Override
    String nextDirection() {

        Position head = getHead();
        Position apple = findApple();

        if (apple == null) {
            return null;
        }

        String direction;
        if (head.getX() != apple.getX()) {
            if (head.getX() > apple.getX()) {
                if (lastDirection.equals("right")) {
                    direction = edgeDirectionHorizontal(head);
                } else {
                    direction = "left";
                }
            } else {
                if (lastDirection.equals("left")) {
                    direction = edgeDirectionHorizontal(head);
                } else {
                    direction = "right";
                }
            }
        } else {
            if (head.getY() > apple.getY()) {
                if (lastDirection.equals("down")) {
                    direction = edgeDirectionVertical(head);
                } else {
                    direction = "up";
                }
            } else {
                if (lastDirection.equals("up")) {
                    direction = edgeDirectionVertical(head);
                } else {
                    direction = "down";
                }
            }
        }
        lastDirection = direction;
        return direction;
    }

    private String edgeDirectionHorizontal(Position head) {
        if (head.getY() == 0) {
            return  "down";
        } else {
            return  "up";
        }
    }

    private String edgeDirectionVertical(Position head) {
        if (head.getX() == 0) {
            return  "right";
        } else {
            return  "left";
        }
    }
}
