import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Mortal extends GameObject implements Damager,Damageable{
    protected int movementSpeed=5;
    protected int face=1;  // 0 --> looks left   1 --> looks right
    // KNOCK
    protected double knockDuration=0;
    protected double knockFinish = Constants.FRAME * 50.0;
    protected boolean knocked;
    //ATTACK
    protected  int attackRange;
    protected  int damage;
    protected boolean attacking;
    protected double attackDuration = 0;
    protected static final double ATTACKFINISH = Constants.FRAME * 60;
    protected boolean inRange = false;

    // IMMUNITY
    protected double immuneDuration = 0;
    protected double IMMUNEFINISH = Constants.FRAME * 20;
    protected boolean immune = false;

    protected int health;
    protected int queue = 0;

    protected Mortal(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
        this.aGravity = true;
    }

    protected void knockTick(){
        if(this.knockDuration > knockFinish && this.knocked){
            this.knocked = false;
            this.knockDuration = 0;
            this.velY = 0;
            this.velX = 0;
        }else{
            this.knockDuration += Constants.FRAME;
        }
    }
    protected void fallTick(){
        if(this.y > Constants.HEIGHT)
            this.health = 0;
    }
    protected void immuneTick(){
        if(immune && immuneDuration < IMMUNEFINISH){
            immuneDuration+= Constants.FRAME;
        }else{
            immune = false;
            immuneDuration = 0;
        }

    }

    protected void healthTick(){
        if(this.health <= 0)
            Handler.removeObject(this);
    }

    protected void knock(int velX,int velY){
        this.velX = velX*2;
        this.knocked = true;
        this.velY = velY;
    }

    public void takeDamage(int damage, int velX, int velY) {
        if(!immune){
            health -= damage;
            immune = true;
            this.immuneDuration = 0;
            this.knock(velX+15, velY+10);
        }
    }

    protected void attackTick(){
        if(attackDuration > ATTACKFINISH){
            attackDuration = 0;
            attacking = false;
        }else
            attackDuration += Constants.FRAME;
    }
    protected void movementTick(){
        if(velX > 0)
            face = 1;
        else if(velX < 0)
            face = 0;
        x += velX;
        y += velY;
    }

    public void giveDamage(Mortal object) {
        object.takeDamage(this.damage,this.velX,this.velY);
    }

    protected void attackInRange(Mortal object){
            if(attacking){
                int objleftX =  object.getX();
                int objrightX = objleftX + object.getWidth();
                boolean rIntersection = objrightX <= x && objrightX >= x - attackRange;
                boolean lIntersection = objleftX <= x+width+attackRange && objleftX >= x+width;
                boolean yIntersection = (object.getY()+object.getVelY()> y && object.getY()+object.getVelY() < y+height) ||
                        (object.getY()+object.getVelY()+object.getHeight()> y && object.getY()+object.getVelY()+object.getHeight() < y+height);

                inRange = yIntersection && (lIntersection || rIntersection);

                if(inRange){
                    if(lIntersection)
                        object.takeDamage(this.damage,15+velX,15);
                    else
                        object.takeDamage(this.damage,-15+velY,15);
                    this.attacking = false;
                }

            }
    }
    protected void drawImage(Graphics g, BufferedImage[] arr){
        if(face == 1)
            g.drawImage(arr[queue/15],x,y,width,height, null);
        else{
            Visual.mirror(arr[queue/15]);
            g.drawImage(arr[queue/15],x,y,width,height, null);
            Visual.mirror(arr[queue/15]);
        }
    }

    public abstract void attack();
    public boolean isKnocked() { return knocked; }
    public void setKnocked(boolean knocked) { this.knocked = knocked; }
}
