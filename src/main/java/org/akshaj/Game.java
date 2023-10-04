package org.akshaj;

public class Game {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    public Game(){
          this.gamePanel=new GamePanel();
          this.gameWindow=new GameWindow(gamePanel);
//          gamePanel.requestFocus(); platform dependent
          gamePanel.requestFocusInWindow();
    }


}
