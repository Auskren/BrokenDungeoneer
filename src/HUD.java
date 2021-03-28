import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HUD {
    private static BufferedImage[] healthImgs = new BufferedImage[5];
    public void loadImgs() throws IOException {
        int j;
        for(int i=0;i<5;i++){
            j = i+1;
            healthImgs[i] = ImageIO.read(new File("assets/HUD/HP/HP_Value_"+j+".png"));
        }
    }

    public void tick(){
    }
    public void render(Graphics g, int translate){
        if(Player.h != 0)
            g.drawImage(healthImgs[Player.h-1],30+translate,30,174,37,null);
    }
}