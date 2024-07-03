import javax.swing.JFrame;

public class FrameScreen extends JFrame {

    GameScreen gameScreen;

    public FrameScreen() {
        gameScreen = new GameScreen();
        add(gameScreen);
    }

    public static void main(String[] args) {
        FrameScreen f = new FrameScreen();
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ensure the application exits on close
    }
}
