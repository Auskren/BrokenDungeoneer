import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
    static LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
    private int episode, prevEpisode;

    public Handler(){ episode = 1; prevEpisode =1;}

    private void episodeTick(){
        if(episode != prevEpisode){
            switch (episode){
                case 1:
                    LevelLoader.levelOne(this);
                    break;
                case 2:
                    LevelLoader.levelTwo(this);
                    break;
                case 3:
                    LevelLoader.levelThree(this);
                    break;
            }
        }
    }

    private void playerTick(){
        Player tempPlayer = null;
        for(int i=0;i<Handler.gameObjects.size();i++){
            if(Handler.gameObjects.get(i).getId() == ID.Player){
                tempPlayer = (Player) Handler.gameObjects.get(i);
                break;
            }
        }
        if(tempPlayer != null)
            tempPlayer.tick();
    }


    public void tick(){
        LevelLoader.tick(this);
        episodeTick();
        playerTick();
        for(int i =0;i< gameObjects.size();i++){
            if(gameObjects.get(i).getId() != ID.Player){
                GameObject tempObject = gameObjects.get(i);
                if(tempObject.aGravity)
                    tempObject.setVelY(7);
                tempObject.checkCollision();
                tempObject.tick();
            }

        }
    }


    public void render(Graphics g){
        GameObject tempObject;

        for(int i=gameObjects.size()-1;i>=0;i--){
            tempObject = gameObjects.get(i);
            if(ID.Projectile != tempObject.id)
                tempObject.render(g);
        }

        for(int i=0;i<gameObjects.size();i++){
            tempObject = gameObjects.get(i);
            if(ID.Projectile == tempObject.id)
                tempObject.render(g);
        }

    }

    public static GameObject findById(ID id){
        GameObject obj = null;
        for(int i=0;i<gameObjects.size();i++)
            if(gameObjects.get(i).getId() == id){
                obj = gameObjects.get(i);
                break;
            }
        return obj;
    }

    public static void addObject(GameObject object){
        gameObjects.add(object);
    }

    public static void removeObject(GameObject object){
        gameObjects.remove(object);
    }

    public void  removeAllObjects(){
        gameObjects.clear();
    }
    // Getters
    public void setEpisode(int episode) {
        this.episode = episode;
    }
    public int getEpisode() {
        return episode;
    }
    public int getPrevEpisode() {
        return prevEpisode;
    }
    // Setters
    public void setPrevEpisode(int prevEpisode) { this.prevEpisode = prevEpisode; }

}
