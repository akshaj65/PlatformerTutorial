package org.akshaj;

public class Game implements Runnable{

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET=120;


    public Game(){

          this.gamePanel=new GamePanel();
          this.gameWindow=new GameWindow(gamePanel);
          gamePanel.requestFocusInWindow();
          startGameLoop();
    }

    public void startGameLoop(){
        gameThread = new Thread(this); //this is the runnable Game class
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1_000_000_000.0 / FPS_SET;  //  1s / 120 frames  = 0.00833s for 1frame
        long lastFrame = System.nanoTime();
        long now;
        int frames=0;
        long lastCheck= System.currentTimeMillis();


         while (true){

             now = System.nanoTime();
             if(now - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
                frames++;
            }



             if(System.currentTimeMillis()-lastCheck >=1000){  //for every one second
                 lastCheck=System.currentTimeMillis();
                 System.out.println("FPS "+frames);
                 frames=0;
             }
         }
    }
}
