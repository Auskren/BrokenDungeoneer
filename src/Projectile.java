import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile extends Interactable implements Damager{
    private static final int damage = 1;
    private boolean gaveDamage = false;
    private double timer= 0;
    private int startX, startY;
    private BufferedImage img;
    public Projectile(int x, int y, int velX, int velY, int width, int height,ID id) throws IOException {
        super(x, y, width, height, id);
        this.velX = velX;
        this.velY = velY;
        this.img = ImageIO.read(new File("assets/Sprites/Constants/Projectile.png"));
        startX = x;
        startY = y;
    }

    public void tick() {
        x+=velX;
        y+=velY;
        checkCollision();
        if(!inRange())
            Handler.removeObject(this);
        giveDamage((Player) Handler.findById(ID.Player));
    }

    private boolean inRange(){
        return  !(startX+ 800 < x || startY + 1000 < y);
    }

    public void render(Graphics g) {
        g.drawImage(img,x,y,width,height,null);
    }

    public void giveDamage(Mortal object) {
        if(onTouch){
            object.takeDamage(damage, this.velX,this.velY);
            Handler.removeObject(this);
        }

    }

    // PROJECTILE SPAWNERS
    public static void spawnProjOnTrue(boolean condition, int x, int y,  int velX, int velY){
        try{
            if(condition){
                Handler.addObject(new Projectile(x,y,velX,velY,Constants.FRB_SIZE,Constants.FRB_SIZE, ID.Projectile));
            }
        }catch (Exception ignored){ }
    }

    public static double spawnProjWithTGaps(boolean condition, int x, int y,  int velX, int velY, double time, double finish){
        if(time >= finish * Constants.FRAME) {
            spawnProjOnTrue(condition, x,y, velX,velY);
            return 0;
        }else
            return time + Constants.FRAME;
    }

}
