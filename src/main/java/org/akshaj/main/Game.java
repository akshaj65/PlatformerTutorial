package org.akshaj.main;

import org.akshaj.entities.Player;
import org.akshaj.levels.LevelManager;

import java.awt.*;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    public final static int  TILES_DEFAULT_SIZE =32;
    public final static float SCALE =2f; //2.4f control this u get different window size
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE) ;
    public final static int GAME_WIDTH = TILES_IN_WIDTH *TILES_SIZE;
    public final static int GAME_HEIGHT = TILES_IN_HEIGHT *TILES_SIZE;

    private final int FPS_SET=120;
    private final int UPS_SET=200;

    private Player player;
    private LevelManager levelManager;


    public Game(){
          initClasses();
          this.gamePanel=new GamePanel(this);
          this.gameWindow=new GameWindow(gamePanel);
          gamePanel.requestFocusInWindow();
          startGameLoop();
    }

    private void initClasses() {
        levelManager= new LevelManager(this);
        player=new Player(200,200,(int)(64*SCALE),(int)(40*SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLvlData());
    }

    public void startGameLoop(){
        gameThread = new Thread(this); //this is the runnable Game class
        gameThread.start();
    }

    public void update(){
       player.update();
       levelManager.update();
    }

    public  void render(Graphics g){
        levelManager.draw(g);  //when this is called first level data is drawn first
        player.render(g);  //above level player is drawn

    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;  //  1s / 120 frames  = 0.00833s for 1frame
        double timeBetweenUpdates = 1_000_000_000.0/UPS_SET;  //no of nanoseconds that should elapse b/w each game update

        long previousTime = System.nanoTime();

        int frames=0;
        int updates=0;
        long lastCheck= System.currentTimeMillis();

        double deltaU=0; //as its double we can  add up the point value also
        double deltaF=0;

        /**        The benefit of this game loop structure is that it decouples the game's update rate from its frame rate.
         This means that the game will update at a consistent rate, regardless of how fast it's able to render frames.  **/
         while (true){

             long currentTime = System.nanoTime();

             deltaU+=(currentTime-previousTime)/timeBetweenUpdates;  //This calculates how many updates should have occurred in the elapsed time
             deltaF+=(currentTime-previousTime)/timePerFrame;
             previousTime=currentTime;

             if(deltaU >=1){
                 update();
                 updates++;
                 deltaU--;
             }
             if(deltaF >=1){
                 gamePanel.repaint();
                 frames++;
                 deltaF--;
             }


             if(System.currentTimeMillis()-lastCheck >=1000){  //for every one second
                 lastCheck=System.currentTimeMillis();
                 System.out.println("FPS "+frames+" | UPS "+updates);
                 updates=0;
                 frames=0;
             }
         }
    }
    public  void windowsFocusLost(){
        player.resetDirBooleans();
    }
    public Player getPlayer(){
        return player;
    }
}
