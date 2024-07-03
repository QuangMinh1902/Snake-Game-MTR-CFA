import javax.swing.*;
import java.awt.*;

public class Snake {
    int doDai = 1;  // Length of the snake, initially 1
    int[] x;  // Array to store the x-coordinates of the snake's segments
    int[] y;  // Array to store the y-coordinates of the snake's segments

    public static final int GO_UP = 1;  // Constant for up direction
    public static final int GO_DOWN = -1;  // Constant for down direction
    public static final int GO_LEFT = 2;  // Constant for left direction
    public static final int GO_RIGHT = -2;  // Constant for right direction

    int vector = Snake.GO_DOWN;  // Current direction of the snake, initially down
    long t1 = 0;  // Time marker to control the snake's movement speed

    public Snake() {
        x = new int[20];
        y = new int[20];
        x[0] = 5;  // Initial x-coordinate of the snake's head
        y[0] = 4;  // Initial y-coordinate of the snake's head
    }

    public void setVector(int vector) {
        if(this.vector != -vector ){
            this.vector = vector;
        }
    }

    public void update() {
        if (System.currentTimeMillis() - t1 > 1000) {
            if (vector == Snake.GO_UP) {
                y[0]--;
            }
            if (vector == Snake.GO_DOWN) {
                y[0]++;
            }
            if (vector == Snake.GO_LEFT) {
                x[0]--;
            }
            if (vector == Snake.GO_RIGHT) {
                x[0]++;
            }
            t1 = System.currentTimeMillis();
        }
    }

    public void paintSnake(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < doDai; i++) {
            g.fillRect(x[i] * 20 + 1, y[i] * 20 + 1, 18, 18);
        }
    }
}
