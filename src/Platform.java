import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Platform extends GameObject{
    private BufferedImage tile;

    public Platform(int x, int y, int width, int height, ID id) throws IOException {
        super(x,y, width,height, id);
        tile = ImageIO.read(new File("assets/Sprites/Constants/brick.png"));
    }

    @Override
    public void collision(GameObject object) {} // they won't move so we don't need it <---- this is a lie

    public void tick() {} // nothing will change, for now...


    public void render(Graphics g) {
        for(int i=y;i<y+height;i+=31){
            for(int j=x;j<x+width;j+=31)
                g.drawImage(tile,j,i,31,31,null);
        }
    }

}
