import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.InputStream;
import java.awt.Font;

public class GameScreen extends JPanel implements Runnable {
    static int[][] bg = new int[20][20];
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;

    static boolean isPlaying = false;
    static boolean enableTextStartGame = true;

    Snake snake;

    Thread thread;
    static boolean isGameOver = false;

    public GameScreen() {
        snake = new Snake();
        Data.loadImage();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
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
        if (!isPlaying && enableTextStartGame) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press SPACE to start", 100, 200);
        }
        if (isGameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Game Over", 100, 250);
        }
    }

    public void paintBg(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH + padding * 2, HEIGHT + padding * 2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (bg[i][j] == 2) {
                    g.drawImage(Data.imagePrey, i * 20, j * 20, null);
                }
            }
        }
    }

    private void paintFrame(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect(0, 0, WIDTH + padding * 2, HEIGHT + padding * 2);
        g.drawRect(1, 1, WIDTH + padding * 2 - 2, HEIGHT + padding * 2 - 2);
        g.drawRect(2, 2, WIDTH + padding * 2 - 4, HEIGHT + padding * 2 - 4);
    }
}