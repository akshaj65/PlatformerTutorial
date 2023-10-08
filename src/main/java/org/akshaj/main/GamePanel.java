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

import static org.akshaj.utils.Constants.PlayerConstants.*;

public class GamePanel extends JPanel {

    private  MouseInputs mouseInputs;
    private float xDelta=100,yDelta=100;
    private BufferedImage bufferedImage;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex,aniSpeed=15;
    private int playerAction=IDLE;
    private int playerDir=-1;
    private boolean moving=false;



    public GamePanel(){
        this.mouseInputs=new MouseInputs(this);
        importImage();
        loadAnimations();
        setPanelSize();
        addKeyListener( new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = bufferedImage.getSubimage((j * 64), (i * 40), 64, 40);
            }
        }
    }
    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/active-assets/player_sprites.png");
        try {
            bufferedImage= ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);  //image size is 32px  1280/32 = 40 image wide    800/32 =25 image in height
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDir=direction;
        moving=true;
    }

    public void setMoving(boolean moving){
        this.moving=moving;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();

        setAnimation();
        updatePos();

        g.drawImage(animations[playerAction][aniIndex],(int)xDelta,(int)yDelta,128+128,80+80,null);

    }

    private void updatePos() {
        if(moving){
            switch (playerDir){
                case Directions.LEFT:
                    xDelta-=5;
                    break;
                 case Directions.DOWN:
                     yDelta+=5;
                     break;
                 case Directions.UP:
                     yDelta-=5;
                     break;
                 case Directions.RIGHT:
                     xDelta+=5;
                     break;
            }
        }
    }

    private void setAnimation() {
        if(moving){
            playerAction=RUNNING;
        }else {
            playerAction=IDLE;
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=getSpriteAmount(playerAction)) aniIndex=0;

        }
    }




}
