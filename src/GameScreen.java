import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GameScreen extends JPanel implements Runnable {
    static int [][] bg = new int[20][20];
    Snake snake;

    Thread thread;
    public GameScreen() {
        snake = new Snake();
        bg[10][10] = 2;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        while (true) {
            snake.update();
            repaint();
            try {
                Thread.sleep(20);
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
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                g.fillRect(i * 20 +1, j * 20 +1, 18, 18);
                if(bg[i][j] == 2) {
                    g.setColor(Color.RED);
                    g.fillRect(i * 20 +1, j * 20 +1, 18, 18);
                    g.setColor(Color.GRAY);
                }
            }
        }
    }
}
