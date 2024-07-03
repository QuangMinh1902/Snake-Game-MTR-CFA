import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

public class GameScreen extends JPanel implements Runnable {
    static int [][] bg = new int[20][20];
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
            snake.update();
            repaint();
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
    }

    public void paintBg(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 400, 400);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (bg[i][j] == 2) {
                   g.drawImage(Data.imagePrey, i * 20 , j * 20  , null);
                }
            }
        }
    }
}
