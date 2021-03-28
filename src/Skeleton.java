import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Skeleton extends Enemy{
    private BufferedImage[] walk = new BufferedImage[4];

    protected Skeleton(int x, int y, int width, int height, int x1, int x2, ID id) throws IOException {
        super(x, y, width, height,x1,x2, id);
        this.attackRange = 5;
        this.damage = 1;
        this.movementSpeed = 3;
        this.patrolx1 = x1;
        this.patrolx2 = x2;
        this.health = 90;
        for(int i=0;i<4;i++)
            walk[i] = ImageIO.read(new File("assets/Sprites/Monsters/Skeleton/Skeleton_Walk_"+i+".png"));
    }

    public void attack() {
        this.attacking = true;
    }

    /*
    public void takeDamage(int damage, int knockX, int knockY) {
        health -= damage;
        this.knock(knockX, knockY);
    }*/

    @Override
    protected void attackInRange(Mortal object) {
                int objleftX = object.getX();
                int objrightX = objleftX + object.getWidth();
                boolean rIntersection = objrightX <= x && objrightX >= x - attackRange;
                boolean lIntersection = objleftX <= x + width + attackRange && objleftX >= x + width;
                boolean yIntersection =
                        ((object.getY() > y && object.getY() < y + height) ||
                        (y > object.getY() && y < object.getY()+object.getHeight()) )
                            ||
                        ((object.getY() + object.getHeight() > y && object.getY() + object.getHeight() < y + height) ||
                        (y+height > object.getY() && y+height < object.getY()+object.getHeight()));

                inRange = yIntersection && (lIntersection || rIntersection);

                if (inRange) {
                    this.attacking = true;
                    if (lIntersection)
                        object.takeDamage(this.damage, 15 + velX, 15);
                    else
                        object.takeDamage(this.damage, -15 + velY, 15);
                    this.attacking = false;
                }
    }



    protected void ai() {
        if(canSee()){
            Player player = (Player) Handler.findById(ID.Player);
            if(player == null) return;
            if((x+width/2) - (player.x+player.width/2) > 0)
                this.velX = -movementSpeed;
            else if((x+width/2) - (player.x+player.width/2) < 0)
                this.velX = movementSpeed;
            attackInRange(player);
        }else
            patrol();
    }


    public void render(Graphics g) {
        g.drawImage(walk[queue/15],x,y,width,height,null);
    }


}
