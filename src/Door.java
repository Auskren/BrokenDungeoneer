import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Door extends Interactable {
    private BufferedImage[] animation = new BufferedImage[4];
    private int queue = 0;
    public Door(int x, int y, int width, int height, ID id) throws IOException {
        super(x, y, width, height, id);
        for(int i=0;i<4;i++)
            animation[i] = ImageIO.read(new File("assets/Props/Doors/Door/Door_Close_"+i+".png"));
    }

    public void tick() {
        checkCollision();
        queue++;
        queue%=60;
    }

    private void drawImage(Graphics g, BufferedImage[] arr){
        g.drawImage(arr[queue/15],x,y,width,height, null);
    }

    public void render(Graphics g) {
        drawImage(g,animation);
    }

}
