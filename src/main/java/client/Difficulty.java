package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Difficulty {

    static String path = "data/difficulty.txt";

    public static String getDifficulty() {
        String difficulty = null;
        try {
            Scanner scanner = new Scanner(new File(path));
            difficulty = scanner.next();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return difficulty;
    }

    public static int setDifficulty(String difficulty) {
        try {
            FileWriter writer = new FileWriter(new File(path), false);
            writer.write(difficulty);
            writer.close();
        } catch (IOException e) {
            return -1;
        }
        return 0;
    }
}
