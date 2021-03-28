import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler = new Handler();

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        Player tempObject;
        tempObject =(Player) Handler.findById(ID.Player);


        switch (key){          // Controls
            case KeyEvent.VK_S:{
                Door door = null;
                door = (Door) Handler.findById(ID.Door);
                if(door != null && door.isOnTouch())
                    handler.setEpisode(handler.getEpisode()+1);
            }break;
            case KeyEvent.VK_W:
                tempObject.setVelY(-5);
                break;
            case KeyEvent.VK_D:
                tempObject.setVelX(5);
                break;
            case KeyEvent.VK_A:
                tempObject.setVelX(-5);
                break;
            case KeyEvent.VK_SPACE:{
                if(!tempObject.floating)  // if not floating
                    tempObject.jump();
            }break;
            case KeyEvent.VK_P:{
                if(!tempObject.isAttacking())
                    tempObject.attack();
            }break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        Player tempObject = null;
        tempObject = (Player)  Handler.findById(ID.Player);
        if(tempObject.getId() != null) {
            if((key == KeyEvent.VK_S && tempObject.getVelY() == 5) || (key == KeyEvent.VK_W && tempObject.getVelY() == -5))tempObject.setVelY(0);
            if((key == KeyEvent.VK_D && tempObject.getVelX() == 5) || (key == KeyEvent.VK_A && tempObject.getVelX() == -5))tempObject.setVelX(0);
            if(key == KeyEvent.VK_SPACE){
                tempObject.setJumped(false);
                tempObject.setJumpDuration(0);
            }
        }

    }

}
