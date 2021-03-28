public abstract class Enemy extends Mortal{
    protected int patrolPoint=2;
    protected int patrolx1, patrolx2;

    public Enemy(int x, int y, int width, int height,int patrolx1,int patrolx2, ID id){
        super(x,y, width,height, id);
        this.patrolx1 = patrolx1;
        this.patrolx2 = patrolx2;
    }

    public void tick() {
        immuneTick();     // check immunity
        floatTick();      // check gravity
        checkCollision(); // check bound clash with platforms
        ai();
        knockTick();      // check knockback
        movementTick();    // check movement
        attackTick();     // check attack
        fallTick();       // check if it fall to the void ( y > height)
        healthTick();     // check health
        queue++;          // animation timer
        queue%=60;
    }

    protected boolean canSee(){
        Player player = (Player) Handler.findById(ID.Player);
        if(player == null)
            return false;
        return Math.abs(this.x - player.x) < 700 && Math.abs(this.y - player.y) < 220;
    }

    protected void patrol(){ //     x1---------x2  they all start from x1
        if(this.patrolPoint == 1){ // move to x1
            if(this.x < this.patrolx1)
                this.patrolPoint = 2;
            else
                this.velX = -movementSpeed;
        }else{     // move to x2
            if(this.x > this.patrolx2)
                this.patrolPoint = 1;
            else
                this.velX = movementSpeed;
        }
    }

    protected abstract void ai();


}
