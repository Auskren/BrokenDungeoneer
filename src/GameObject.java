import java.awt.Graphics;

public abstract class GameObject implements Drawable{
    protected int x, y; // position data
    protected ID id;
    protected int velX, velY; // velocity vectors
    protected int width, height;
    protected boolean aGravity = false;
    protected boolean floating = false;
    protected boolean debug;
    protected String ImagePath;
    public GameObject(int x, int y, int width, int height, ID id){
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.id = id;
    }

    protected void floatTick(){
        this.floating = true;
        if(isFloating() && aGravity)
            setVelY(7);
    }


    public void collision(GameObject object){
        boolean notImmobile = this.aGravity && !object.aGravity; // only

        if(notImmobile){
            boolean yIntersection = (y >= object.getY() && y < object.getY()+object.getHeight()) ||
                                    (y+height >= object.getY() && y+height < object.getY()+object.getHeight());
            boolean xIntersection = (x > object.getX() && x < object.getX()+object.getWidth()) ||
                                    (x+width > object.getX() && x+width < object.getX()+object.getWidth());
            // X axis
            if(x+velX < object.getX()+object.getWidth() && x+width > object.getX()+object.getWidth() && yIntersection){// if they clash on the right side of the object
                x =  Game.clamp(x+velX,object.getX()+object.getWidth(),x+velX);
                setVelX(0);
            }else if(x < object.getX() && x+width+velX > object.getX() && yIntersection) { // if they clash on the left side of the object
                x =  Game.clamp(x+width+velX,0,object.getX()) - (width+1); // width > 0 anyway
                setVelX(0);
            }

            // Y axis
            if(y+height > object.getY()+object.getHeight() && y+velY < object.getY()+object.getHeight() && xIntersection){
                // object UP
                y = Game.clamp(y+velY,object.getY()+object.getHeight(),Constants.INF);
                setVelY(0);
            } else if(y < object.getY() && y+height+velY > object.getY() && xIntersection){
                // object DOWN
                y = Game.clamp(y+height+velY,0,object.getY()) - (height+1);
                setVelY(0);
                setFloating(false);
            }

        }


    }

    public void checkCollision(){
        for(int j =0;j< Handler.gameObjects.size();j++){
            if(Handler.gameObjects.get(j) != this){
                GameObject collisionObj = Handler.gameObjects.get(j);
                this.collision(collisionObj);
            }
        }
    }

    // Setters
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public void setId(ID id){ this.id = id; }
    public void setVelX(int velX) { this.velX = velX; }
    public void setVelY(int velY) { this.velY = velY; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) {this.height = height; }
    public void setFloating(boolean floating) { this.floating = floating; }
    public void setaGravity(boolean aGravity) { this.aGravity = aGravity; }

    // Getters
    public int getX(){ return x; }
    public int getY(){ return y; }
    public ID getId(){ return id; }
    public int getVelX() { return velX; }
    public int getVelY() { return velY; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
    public boolean isFloating() { return floating; }
    public boolean isaGravity() { return aGravity; }

}
