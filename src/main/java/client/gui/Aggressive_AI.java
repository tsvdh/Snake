package client.gui;

import client.Position;

class Aggressive_AI extends AI{

    private String lastDirection = "none";

    @Override
    String nextDirection() {

        head = getHead();
        apple = findApple();

        if (apple == null) {
            return null;
        }

        String direction;
        if (head.getX() != apple.getX()) {
            if (head.getX() > apple.getX()) {
                if (lastDirection.equals("right")) {
                    direction = directionHorizontal180(head);
                } else {
                    Position left = head.left();
                    if (snakeList.indexOf(gridArray[left.getY()][left.getX()]) != -1) {
                        direction = directionForParallelHorizontal(left);
                    } else {
                        direction = "left";
                    }
                }
            } else {
                if (lastDirection.equals("left")) {
                    direction = directionHorizontal180(head);
                } else {
                    Position right = head.right();
                    if (snakeList.indexOf(gridArray[right.getY()][right.getX()]) != -1) {
                        direction = directionForParallelHorizontal(right);
                    } else {
                        direction = "right";
                    }
                }
            }
        } else {
            if (head.getY() > apple.getY()) {
                if (lastDirection.equals("down")) {
                    direction = directionVertical180(head);
                } else {
                    Position up = head.up();
                    if (snakeList.indexOf(gridArray[up.getY()][up.getX()]) != -1) {
                        direction = directionForParallelVertical(up);
                    } else {
                        direction = "up";
                    }
                }
            } else {
                if (lastDirection.equals("up")) {
                    direction = directionVertical180(head);
                } else {
                    Position down = head.down();
                    if (snakeList.indexOf(gridArray[down.getY()][down.getX()]) != -1) {
                        direction = directionForParallelVertical(down);
                    } else {
                        direction = "down";
                    }
                }
            }
        }
        lastDirection = direction;
        return direction;
    }

    private String directionHorizontal180(Position head) {
        if (head.getY() == 0) {
            return  "down";
        } else {
            return  "up";
        }
    }

    private String directionVertical180(Position head) {
        if (head.getX() == 0) {
            return  "right";
        } else {
            return  "left";
        }
    }

    private String directionForParallelHorizontal(Position position) {
        Position up = position.up();
        Position down = position.down();

        int upIndex = snakeList.indexOf(gridArray[up.getY()][up.getX()]);
        int downIndex = snakeList.indexOf(gridArray[down.getY()][down.getX()]);

        if (upIndex == -1) {
            return "up";
        }
        if (downIndex == -1) {
            return "down";
        }

        if (upIndex > downIndex) {
            return "up";
        } else {
            return "down";
        }
    }

    private String directionForParallelVertical(Position position) {
        Position left = position.left();
        Position right = position.right();

        int leftIndex = snakeList.indexOf(gridArray[left.getY()][left.getX()]);
        int rightIndex = snakeList.indexOf(gridArray[right.getY()][right.getX()]);

        if (leftIndex == -1) {
            return "left";
        }
        if (rightIndex == -1) {
            return "right";
        }

        if (leftIndex > rightIndex) {
            return "left";
        } else {
            return "right";
        }
    }
}
