import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    private static  final  long serialVersionUID = 1550691097823471818L;



    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private Random r;
    private HUD hud;
    private int translateHUD;

    public Game(){
        new Window(Constants.WIDTH, Constants.HEIGHT, "Broken Dungoneer", this);
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        hud = new HUD();
        try{
            hud.loadImgs();
        }catch(Exception ignored){ }
        r = new Random();
        LevelLoader.levelOne(handler);

    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static int clamp(int var, int min, int max){
        if (var < min)
            return var = min;
        if(var > max)
            return var = max;
        return var;
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;  // frame per second
        double ns = 1000000000 / amountOfTicks; // ns == 1^10 nanoseconds == 1 second  / 60
        double delta = 0;
        long timer = System.currentTimeMillis();  // starting time
        int frames = 0;


        while(running){
            long now = System.nanoTime();  // tick start time
            delta += (now - lastTime) / ns; //
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
        hud.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0, Constants.WIDTH, Constants.HEIGHT);
        Player tempObject = null;
        tempObject = (Player) Handler.findById(ID.Player);
        translateHUD = 0;
        if(tempObject != null){
            g.translate(Constants.WIDTH/2-tempObject.getX(), 0);
            translateHUD = tempObject.getX()- Constants.WIDTH/2;
        }
        handler.render(g);
        hud.render(g, translateHUD);
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}
