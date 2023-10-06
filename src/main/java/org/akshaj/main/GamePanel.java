package org.akshaj.main;

import org.akshaj.inputs.KeyBoardInputs;
import org.akshaj.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private  MouseInputs mouseInputs;
    private float xDelta=100,yDelta=100;
    private BufferedImage bufferedImage;
    private  BufferedImage subImg;


    public GamePanel(){
        this.mouseInputs=new MouseInputs(this);
        
        importImage();
        setPanelSize();
        addKeyListener( new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/active-assets/player_sprites.png");
        try {
            bufferedImage= ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);  //image size is 32px  1280/32 = 40 image wide    800/32 =25 image in height
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
    }
    public void changeYDelta(int value){
        this.yDelta+=value;
    }

    public void setRectPos(int x, int y){
        this.xDelta=x;
        this.yDelta=y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

//        g.drawImage(bufferedImage.getSubimage(0,0,64,40),0,0,128,80,null); //image should be 32*32 but including the spaces surrounding it takes 64 and 40
                                                                            //to make the image bigger  double the width  and height
        subImg= bufferedImage.getSubimage(1 * 64,8 * 40,64,40); //we can access the other subimage by mulply pos with w snd h
        g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null); //image should be 32*32 but including the spaces surrounding it takes 64 and 40

    }


}
