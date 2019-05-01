package client;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Difficulty {

    static String path = "data/difficulty.txt";

    /**
     * Gets the difficulty from the file.
     * @return The difficulty.
     */
    public static String getDifficulty() {
        try {
            Scanner scanner = new Scanner(new File(path));
            return scanner.next();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Sets the difficulty.
     * @param difficulty The difficulty to set it to.
     * @return 0 if the read was successful, -1 if it was not.
     */
    public static int setDifficulty(String difficulty) {
        try {
            FileWriter writer = new FileWriter(new File(path), false);
            writer.write(difficulty);
            writer.close();
            return 0;
        } catch (IOException e) {
            return -1;
        }
    }
}
