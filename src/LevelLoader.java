public  class LevelLoader {
    private static void levelChanged(Handler handler){
        handler.setPrevEpisode(handler.getEpisode());
    }

    public static void levelOne(Handler handler){
        handler.removeAllObjects();
        try{
            // mortals
            Handler.addObject(new Skeleton(31*12, 31*3,40,85,31*12,31*24,ID.Skeleton));
            Handler.addObject(new Sorcerer(31*81, 31*18,50,120,31*81,31*87,ID.Sorcerer));
            Handler.addObject(new Player(100, 31*18,53,74,ID.Player));

            // interactable
            Handler.addObject(new HeartCon(31*14,31*11,25,25,ID.Consumable));
            Handler.addObject(new Door(31*39,114,133,126,ID.Door));

            // platforms
            Handler.addObject(new Platform(31*-3,0, 31*3, 31*26, ID.Platform));
            Handler.addObject(new Platform(31*8, 31*15, 31*3, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31*2, 31*10, 31*3, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31*9, 31*5, 31*3, 31* 2, ID.Platform));
            Handler.addObject(new Platform(0, 31*21, 31*42, 31* 3, ID.Platform));
            Handler.addObject(new Platform(31*10,31*5, 31*3, 31*20, ID.Platform));
            Handler.addObject(new Platform(31*10, 31*5, 31*20, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31*10, 31*12, 31*18, 31, ID.Platform));
            Handler.addObject(new Platform(31*32, 31*12, 31*2, 31, ID.Platform));
            Handler.addObject(new Platform(31*33,0, 31*5, 31*14, ID.Platform));
            Handler.addObject(new Platform(31 * 54, 31*21, 31*5, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31 * 67, 31*21, 31*5, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31 * 80, 31*21, 31*12, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31*92,0, 31*3, 31*24, ID.Platform));
            Handler.addObject(new Platform(31 * 54, 31*9, 31*5, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31 * 67, 31*9, 31*5, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31 * 80, 31*12, 31*4, 31* 2, ID.Platform));
            Handler.addObject(new Platform(31 * 37, 31*7, 31*7, 31* 2, ID.Platform));
            Handler.addObject(new Platform(0, -31*2, 31*33, 31* 3, ID.Platform));
            // background
            Handler.addObject(new Background(5));


        }catch (Exception e){
            return;
        }
        levelChanged(handler);
    }

    public static void levelTwo(Handler handler){
        handler.removeAllObjects();

        try{
            Handler.addObject(new Skeleton(100, Constants.HEIGHT*11/12-360,30,50,120,240,ID.Skeleton));
            Handler.addObject(new Sorcerer(Constants.WIDTH*2,Constants.HEIGHT*11/12-150,28,62, Constants.WIDTH,Constants.WIDTH*3-50,ID.Sorcerer));
            Handler.addObject(new Player(Constants.WIDTH/2, Constants.HEIGHT/2,53,74,ID.Player));
            Handler.addObject(new Platform(0, Constants.HEIGHT*11/12, Constants.WIDTH*4, Constants.HEIGHT/12, ID.Platform));
        }catch (Exception ignored){ }
        levelChanged(handler);
    }

    public static void levelThree(Handler handler){
        handler.removeAllObjects();
        levelChanged(handler);
    }
    public static void tick(Handler handler){
        Player temp = null;
        temp = (Player) Handler.findById(ID.Player);

        if(temp == null)  return;

        switch (handler.getEpisode()){
            case 1:{
                levelOneTick(temp);
            }break;
            case 2:{
                levelTwoTick(temp);
            }break;
            case 3:{
                levelThreeTick(temp);
            }break;
        }
    }



    static double[] spawnedEp1 = new double[3];
    private static void levelOneTick(Player temp) {
        spawnedEp1[0] = Projectile.spawnProjWithTGaps(true, 31*-3,31*14,10,0,spawnedEp1[0],180);
        spawnedEp1[1] = Projectile.spawnProjWithTGaps(true, 31*10,31*9,-10,0,spawnedEp1[1],180);
        spawnedEp1[2] = Projectile.spawnProjWithTGaps(true, 31*92,31*8,-10,0,spawnedEp1[2],150);
    }

    private static void levelTwoTick(Player temp) {
    }

    private static void levelThreeTick(Player temp) {
    }



}
