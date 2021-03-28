import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HeartCon extends Interactable {
    private BufferedImage[] animation = new BufferedImage[4];
    private int queue = 0;
    public HeartCon(int x, int y, int width, int height, ID id) throws IOException {
        super(x, y, width, height, id);
        for(int i=0;i<4;i++)
            animation[i] = ImageIO.read(new File("assets/Items/Heart/HP_"+i+".png"));
    }

    @Override
    public void tick() {
        checkCollision();
        healTick();
        queue++;
        queue%=60;
    }

    private void drawImage(Graphics g, BufferedImage[] arr){
        g.drawImage(arr[queue/15],x,y,width,height, null);
    }

    public void render(Graphics g) {
        drawImage(g,animation);
    }

    private void heal(Player player){
        if(this.onTouch){
            int healAmount = 1;
            player.regenHealth(healAmount);
            Handler.gameObjects.remove(this);
        }
    }

    public void healTick(){
        Player temp = (Player) Handler.findById(ID.Player);
        if(temp != null)
            heal(temp);
    }

}
