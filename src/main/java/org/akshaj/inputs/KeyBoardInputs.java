package org.akshaj.inputs;


import org.akshaj.main.GamePanel;
import org.akshaj.utils.Constants;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.akshaj.utils.Constants.*;

public class KeyBoardInputs implements KeyListener {
    private GamePanel gamePanel;
    public KeyBoardInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
       switch (keyEvent.getKeyCode()){
           case KeyEvent.VK_W -> gamePanel.setDirection(Directions.UP);
           case KeyEvent.VK_A -> gamePanel.setDirection(Directions.LEFT);
           case KeyEvent.VK_S -> gamePanel.setDirection(Directions.DOWN);
           case KeyEvent.VK_D -> gamePanel.setDirection(Directions.RIGHT);
       }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_S -> gamePanel.setMoving(false);
        }
    }
}
