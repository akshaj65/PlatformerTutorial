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
           case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(true);
           case KeyEvent.VK_A -> gamePanel.getGame().getPlayer().setLeft(true);
           case  KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(true);
           case   KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(true);
           case   KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(true);
       }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_W -> gamePanel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_A ->gamePanel.getGame().getPlayer().setLeft(false);
            case  KeyEvent.VK_D -> gamePanel.getGame().getPlayer().setRight(false);
            case   KeyEvent.VK_S -> gamePanel.getGame().getPlayer().setDown(false);
            case   KeyEvent.VK_SPACE -> gamePanel.getGame().getPlayer().setJump(false);

        }
    }
}
