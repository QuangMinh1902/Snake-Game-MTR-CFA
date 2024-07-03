import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class GameScreen extends JPanel implements Runnable {
    static int [][] bg = new int[20][20];
    static int padding = 10;
    static int WIDTH = 400;
    static int HEIGHT = 400;

    static boolean isPlaying = false;

    Snake snake;

    Thread thread;
    public GameScreen() {
        snake = new Snake();
        Data.loadImage();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            if (isPlaying) {
                snake.update();
                repaint();
            }
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Ensure the panel is properly rendered before custom drawing
        paintBg(g);
        snake.paintSnake(g);
        this.paintFrame(g);
    }

    public void paintBg(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH + padding*2, HEIGHT + padding*2);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (bg[i][j] == 2) {
                   g.drawImage(Data.imagePrey, i * 20 , j * 20  , null);
                }
            }
        }

    }

    private void paintFrame(Graphics g) {
        g.setColor(Color.ORANGE);
        g.drawRect(0, 0, WIDTH + padding*2, HEIGHT + padding*2);
        g.drawRect(1, 1, WIDTH + padding*2 -2, HEIGHT + padding*2 -2);
        g.drawRect(2, 2, WIDTH + padding*2 -4, HEIGHT + padding*2 -4);
    }
}
