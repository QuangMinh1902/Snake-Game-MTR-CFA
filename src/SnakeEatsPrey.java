import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class SnakeEatsPrey extends JFrame {

    GameScreen gameScreen;

    public SnakeEatsPrey() {
        setSize(650, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        this.addKeyListener(new handler());
        setVisible(true);
    }

    public static void main(String[] args) {
        SnakeEatsPrey f = new SnakeEatsPrey();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            HighScoreManager.saveHighScore(GameScreen.highScore);
        }));
    }

    private class handler implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                gameScreen.snake.setVector(Snake.GO_UP);
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                gameScreen.snake.setVector(Snake.GO_RIGHT);
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                gameScreen.snake.setVector(Snake.GO_DOWN);
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                gameScreen.snake.setVector(Snake.GO_LEFT);
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                gameScreen.isPlaying = !gameScreen.isPlaying;
                if (gameScreen.isGameOver) {
                    gameScreen.isGameOver = false;
                    gameScreen.snake.resetGame();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Method is required to be overridden but can be left empty
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Method is required to be overridden but can be left empty
        }
    }
}
