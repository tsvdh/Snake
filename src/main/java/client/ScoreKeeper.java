package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class ScoreKeeper {

    static String path = "data/score.txt";

    static int getScore() {
        int score = -1;
        try {
            Scanner scanner = new Scanner(new File(path));
            score = scanner.nextInt();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }

    static void setScore(int score) {
        try {
            FileWriter writer = new FileWriter(new File(path), false);
            writer.write(String.valueOf(score));
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
