package Model;
import java.io.*;

public class HighScoreManager {
    private static final String FILE_PATH = "highscore.txt";

    public static int loadHighScore() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            // If the file doesn't exist, create it and write 0 as the initial high score
            try {
                file.createNewFile();
                saveHighScore(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void saveHighScore(int score) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}