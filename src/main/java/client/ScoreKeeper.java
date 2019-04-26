package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ScoreKeeper {

    static int getScore() {
        int score = -1;
        try {
            Scanner scanner = new Scanner(new File("data/score.txt"));
            score = scanner.nextInt();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }

    static void setScore(int score) {

    }

    public static void main(String[] args) {
        System.out.println(getScore());
    }
}
