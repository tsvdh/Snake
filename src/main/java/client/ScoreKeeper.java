package client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ScoreKeeper {

    static int getScore() {
        int score = -1;
        try {
            Scanner scanner = new Scanner(new File("data/score.txt"));
            score = scanner.nextInt();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return score;
    }

    static void setScore(int score) {
        try {
            FileWriter writer = new FileWriter(new File("data/score.txt"), false);
            writer.write(String.valueOf(score));
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
