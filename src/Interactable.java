import java.awt.*;

public abstract class Interactable extends GameObject{
    protected boolean onTouch = false;
    public Interactable(int x, int y, int width, int height, ID id) {
        super(x, y, width, height, id);
    }

    @Override
    public void collision(GameObject player) {
        boolean xIntersection = (player.getX()+player.getVelX()> x && player.getX()+player.getVelX() < x+width) ||
                (player.getX()+player.getVelX()+player.getWidth()> x && player.getX()+player.getVelX()+player.getWidth() < x+width);
        boolean yIntersection = (player.getY()+player.getVelY()> y && player.getY()+player.getVelY() < y+height) ||
                (player.getY()+player.getVelY()+player.getHeight()> y && player.getY()+player.getVelY()+player.getHeight() < y+height);
        onTouch = yIntersection && xIntersection && player.getId() == ID.Player;
        if(!onTouch){
            if( (   (player.x < x && player.x+player.width > x) ||
                    (player.x< x+width && player.x+player.width > x+width))
                    && yIntersection)
                onTouch = true;
            else if(((player.y < y && player.y+player.width > y )||
                    (player.y < y+width && player.y+width > y+width))
                    && xIntersection)
                onTouch = true;
        }
    }

    @Override
    public void checkCollision() {
        Player temp = (Player) Handler.findById(ID.Player);
        collision(temp);
    }

    public void setOnTouch(boolean onTouch) { this.onTouch = onTouch; }
    public boolean isOnTouch() { return onTouch; }
}
