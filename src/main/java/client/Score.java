package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Score {

    static String path = "data/score.txt";

    /**
     * Gets the score from the file.
     * @return The score.
     */
    public static int getScore() {
        try {
            Scanner scanner = new Scanner(new File(path));
            return scanner.nextInt();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Sets the score.
     * @param score The score to set it to.
     * @return 0 if the read was successful, -1 if it was not.
     */
    public static int setScore(int score) {
        try {
            FileWriter writer = new FileWriter(new File(path), false);
            writer.write(String.valueOf(score));
            writer.close();
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
