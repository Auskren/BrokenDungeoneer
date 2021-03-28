import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math.*;

public class Sorcerer extends Enemy {
    private BufferedImage[] walk = new BufferedImage[4];
    public Sorcerer(int x, int y, int width, int height,int x1,int x2, ID id) throws IOException {
        super(x, y, width, height,x1,x2, id);
        this.health = 120;
        this.movementSpeed = 2;
        for(int i=0;i<4;i++){
            walk[i] = ImageIO.read(new File("assets/Sprites/Monsters/Sorcerer/Sorcerer_Walk_"+i+".png"));
        }
    }
    private double projectileSpawnDuration =0;
    private final double BULLETSPAWNGAP = 110;
    @Override
    protected void ai() {

        if(canSee()){
            this.velX = 0;
            boolean side; // true:right    ---  false:left
            Player player = (Player) Handler.findById(ID.Player);
            double tempVX = player.x - this.x,
                        tempVY= player.y - this.y, tangent;
            tangent = tempVY/tempVX;
            if(tempVX > 0)
                tempVX = 10;
            else if(tempVX <0)
                tempVX = -10;
            this.projectileSpawnDuration = Projectile.spawnProjWithTGaps(true,x+(width-Constants.FRB_SIZE)/2,y+(height-Constants.FRB_SIZE)/2,(int)Math.round(tempVX),(int)Math.round(tempVX * tangent),this.projectileSpawnDuration,BULLETSPAWNGAP);
        }else
            patrol();

    }

    @Override
    public void attack(){}

    public void giveDamage(Mortal object) { }



    public void render(Graphics g) {
        drawImage(g,walk);
    }

}
