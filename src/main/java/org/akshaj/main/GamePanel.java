package org.akshaj.main;

import org.akshaj.inputs.KeyBoardInputs;
import org.akshaj.inputs.MouseInputs;
import org.akshaj.utils.Constants.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;

import static org.akshaj.utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private  MouseInputs mouseInputs;

    private Game game;

    public GamePanel(Game game){
        this.game=game;
        this.mouseInputs=new MouseInputs(this);
        setPanelSize();
        addKeyListener( new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);  //image size is 32px  1280/32 = 40 image wide    800/32 =25 image in height
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.render(g);
    }

    public Game getGame(){
        return game;
    }

}
