import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;

public class Data {
    public static Image imageHead;
    public static Image imageBody;
    public static Image imagePrey;

    public static void loadImage() {
        try {
            InputStream head = Data.class.getResourceAsStream("assets/head.png");
            InputStream body = Data.class.getResourceAsStream("assets/body.png");
            InputStream prey = Data.class.getResourceAsStream("assets/worm.png");
            if (head != null && body != null && prey != null) {
                 imageHead = ImageIO.read(head);
                 imageBody = ImageIO.read(body);
                 imagePrey = ImageIO.read(prey);
                // Resize the image to desired dimensions, for example, 50x50 pixels
                imageHead = imageHead.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                imageBody = imageBody.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
                imagePrey = imagePrey.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            } else {
                System.err.println("Could not find the image file.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
