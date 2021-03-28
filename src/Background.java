import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Background extends GameObject implements Drawable{
    private BufferedImage bck;
    private int count;

    public Background(int count) throws IOException {
        super(0,0,1276,718,ID.Background);
        bck = ImageIO.read(new File("assets/Sprites/Constants/Background.jpg"));
        this.count = count;
    }

    public void tick() { }

    public void render(Graphics g) {
        for(int i = 0; i<=count; i++)
            g.drawImage(bck,(i-1)* width,0, width, height,null);
    }
}
