import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player extends Mortal{
    public double jumpDuration=0;
    public boolean jumped;
    public static int h = 5;
    public static final double JUMPFINISH = Constants.FRAME * 45.0; // 45/60 == 0.75 sec.  45* 7 = 315 px
    private static BufferedImage[] idle = new BufferedImage[4],
                                   walk = new BufferedImage[4],
                                   attackAnimation = new BufferedImage[4];


    private void loadAnimations() throws IOException {
        String temp;
        for(int i=0;i<4;i++){
            temp = "assets/Sprites/Player/Sword/Player_Idle_Sword_Defence0_"+i+".png";
            idle[i] = ImageIO.read(new File(temp));
            temp ="assets/Sprites/Player/Sword/Player_Walk_Sword_Defence0_"+i+".png";
            walk[i] = ImageIO.read(new File(temp));
            temp = "assets/Sprites/Player/Sword/Player_Attack_Sword_Defence0_"+i+".png";
            attackAnimation[i] = ImageIO.read(new File(temp));
        }
    }

    public Player(int x, int y, int width, int height, ID id) throws IOException {
        super(x,y, width,height, id);
        this.health = Player.h;
        this.attackRange = 35;
        this.damage = 30;
        loadAnimations();
        jumped = false;

    }
    public void attack() {
        this.attacking = true;
    }



    public void jump(){
        setJumped(true);
    }
    private void jumpTick(){
        if(isJumped()){
            if(getJumpDuration() >JUMPFINISH || (this.getJumpDuration() != 0 && this.velY == 0)){
                setJumped(false);
                setJumpDuration(0);
            }
            else{
                setJumpDuration(getJumpDuration()+Constants.FRAME);
                setVelY(-7);
            }
        }
    }

    @Override
    public void healthTick() {
        Player.h = this.health;
        if(Player.h == 0)
            System.exit(0);
    }

    @Override
    public void collision(GameObject object) {
        switch (object.getId()){
            case Skeleton:{
                attackInRange((Skeleton) object);
            }break;
            case Sorcerer:{
                attackInRange((Sorcerer) object);
            }break;
            case Platform:{
                super.collision(object);
            }break;
        }
    }

    @Override
    protected void floatTick() {
        this.floating = true;
        if(!jumped)
            super.floatTick();

    }

    public void tick(){
        immuneTick();
        floatTick(); // gravity
        jumpTick();
        checkCollision();
        movementTick();
        knockTick();
        attackTick();
        fallTick();
        healthTick();
        queue++;
        queue %= 60;

    }

    public void render(Graphics g){
        if(attacking)
            drawImage(g,attackAnimation);
        else if(this.velX != 0)
            drawImage(g,walk);
        else
            drawImage(g,idle);

    }


    public void regenHealth(int heal){
        this.setHealth(Game.clamp(this.getHealth()+heal,0,5));
    }

    // Setters
    public void setJumped(boolean jumped){ this.jumped = jumped; }
    public void setJumpDuration(double jumpDuration){ this.jumpDuration = jumpDuration; }
    public void setHealth(int health) { this.health = health; }

    // Getters
    public boolean isJumped() { return jumped; }
    public double getJumpDuration() { return jumpDuration; }
    public int getHealth() { return health; }
    public boolean isAttacking() { return attacking; }
}
