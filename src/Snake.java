import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Snake {
    int length = 3;  // Length of the snake, initially 1
    int[] x;  // Array to store the x-coordinates of the snake's segments
    int[] y;  // Array to store the y-coordinates of the snake's segments

    public static final int GO_UP = 1;  // Constant for up direction
    public static final int GO_DOWN = -1;  // Constant for down direction
    public static final int GO_LEFT = 2;  // Constant for left direction
    public static final int GO_RIGHT = -2;  // Constant for right direction

    int vector = Snake.GO_DOWN;  // Current direction of the snake, initially down
    long t1 = 0;  // Time marker to control the snake's movement speed
    int speed = 300;
    int maxLen = 10;


    public Snake() {
        x = new int[20];
        y = new int[20];
        x[0] = 5;  // Initial x-coordinate of the snake's head
        y[0] = 4;  // Initial y-coordinate of the snake's head

        x[1] =5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
    }

    public void resetGame(){
        this.x = new int[20];
        this.y = new int[20];

        x[0] = 5;  // Initial x-coordinate of the snake's head
        y[0] = 4;  // Initial y-coordinate of the snake's head

        x[1] =5;
        y[1] = 3;
        x[2] = 5;
        y[2] = 2;
        this.length = 3;
    }

    public void setVector(int vector) {
        if(this.vector != -vector ){
            this.vector = vector;
        }
    }

    public Point getCoordinates() {
       Random r = new Random();
       int x;
       int y;
       do {
           x = r.nextInt(19);
           y = r.nextInt(19);
       } while (this.checkCoordinate(x,y));
       return new Point(x, y);
    }

    public boolean checkCoordinate(int x1,int y1) {
       for(int i = 0; i < this.length; i++) {
          if(x[i] == x1 && y[i] == y1) {
              return true;
          }
       }
       return false;
    }

    public void update() {
        if(this.length == this.maxLen) {
            GameScreen.isPlaying = false;
            this.resetGame();
            this.speed = (int) (this.speed * 0.8);
        }

        for(int i = 1; i < this.length ; i++) {
            if(x[0] == x[i] && y[0] == y[i]) {
               GameScreen.isPlaying = false;
               GameScreen.isGameOver = true;
            }
        }

        if (System.currentTimeMillis() - t1 > this.speed) {
            if(GameScreen.bg[x[0]][y[0]] == 2) {
                length++;
                GameScreen.bg[x[0]][y[0]] = 0;
                GameScreen.bg[this.getCoordinates().x][this.getCoordinates().y] = 2;
            }

            for(int i = this.length -1; i > 0; i--) {
                x[i] = x[i-1];
                y[i] = y[i-1];
            }

            // update the head of the snake
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

            // check if the snake is out of bounds
            if(x[0] < 0) x[0] = 19;
            if(x[0] > 19) x[0] = 0;
            if(y[0] < 0) y[0] = 19;
            if(y[0] > 19) y[0] = 0;

            t1 = System.currentTimeMillis();
        }
    }

    public void paintSnake(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 1; i < this.length; i++) {
            g.drawImage(Data.imageBody, x[i] * 20, y[i] * 20 , null);
        }
        g.drawImage(Data.imageHead, x[0] * 20  , y[0] * 20 , null);
    }
}
