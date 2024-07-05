import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import Model.*;

public class GameScreen extends JPanel implements Runnable {
    static int[][] bg = new int[20][20];
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;

    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;

    Snake snake;

    Thread thread;
    static int CurrentLevel = 1;
    static int point = 0;
    static boolean isGameOver = false;
    static int highScore = HighScoreManager.loadHighScore(); // Load the high score
    static int nextLevelPoint; // Initialize the points required for the next level

    public GameScreen() {
        snake = new Snake();
        Data.loadImage();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
        nextLevelPoint = (snake.maxLen - snake.length) * 100;
    }

    public void run() {
        long t2 = 0;
        while (true) {
            if (System.currentTimeMillis() - t2 > 500) {
                enableTextStartGame = !enableTextStartGame;
                t2 = System.currentTimeMillis();
            }
            if (isPlaying) {
                snake.update();
                repaint();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!isPlaying) {
                repaint();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Ensure the panel is properly rendered before custom drawing
        paintBg(g);
        snake.paintSnake(g);
        this.paintFrame(g);
        if (!isPlaying && enableTextStartGame && !isGameOver) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press SPACE to start", 100, 200);
        }
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Game Over", 100, 250);
            g.setColor(Color.YELLOW);
            g.drawString("Press Space to restart", 100, 200);
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString("Level: " + CurrentLevel, 450, 100); // Display the current level

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Points: " + point, 450, 150);
        g.drawString("High Score: " + highScore, 450, 200); // Display the high score
        g.drawString("Next Level: " + nextLevelPoint, 450, 250); // Display the points for the next level
    }

    public void paintBg(Graphics g) {
        g.setColor(new Color(0x0072ff));
        g.fillRect(0, 0, WIDTH + padding * 2 + 250, HEIGHT + padding * 2);

        // Draw prey image only where bg[i][j] == 2 and there's no snake body
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (bg[i][j] == 2) {
                    boolean isSnakeBody = false;
                    for (int k = 0; k < snake.length; k++) {
                        if (snake.x[k] == i && snake.y[k] == j) {
                            isSnakeBody = true;
                            break;
                        }
                    }
                    if (!isSnakeBody) {
                        g.drawImage(Data.imagePrey, i * 20, j * 20, null);
                    }
                }
            }
        }
    }


    private void paintFrame(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect(0, 0, WIDTH + padding * 2, HEIGHT + padding * 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 2, HEIGHT + padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + padding * 2 - 4, HEIGHT + padding * 2 - 4);

        g.drawRect(0, 0, WIDTH + padding * 2 + 250, HEIGHT + padding * 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 2 + 250, HEIGHT + padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + padding * 2 - 4 + 250, HEIGHT + padding * 2 - 4);
    }
}