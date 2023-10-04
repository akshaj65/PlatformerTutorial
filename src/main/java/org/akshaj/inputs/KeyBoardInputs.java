package org.akshaj.inputs;

import org.akshaj.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
           case KeyEvent.VK_W -> gamePanel.changeYDelta(-5);
           case KeyEvent.VK_A -> gamePanel.changeXDelta(-5);
           case KeyEvent.VK_S -> gamePanel.changeYDelta(5);
           case KeyEvent.VK_D -> gamePanel.changeXDelta(5);
       }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
