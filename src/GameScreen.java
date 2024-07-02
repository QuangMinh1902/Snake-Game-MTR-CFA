import javax.swing.JPanel;
import java.awt.Graphics;

public class GameScreen extends JPanel {
    int a = 0;
    public GameScreen() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(10, 10, 100, 100);
    }

    public void run (){
        while(true){
           a++ ;
           System.out.println(String.valueOf(a));
        }
    }
}
