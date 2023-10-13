package org.akshaj.entities;

import org.akshaj.utils.Constants;
import org.akshaj.utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static org.akshaj.utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex,aniSpeed=15;
    private int playerAction=IDLE;
    private boolean left,right,up,down;
    private float playerSpeed =2.0f;
    private boolean moving=false,attacking=false;

    public Player(float x,float y,int width,int height){
        super(x,y,width,height);
        loadAnimations();
    }

    public void update(){
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    private void updatePos() {
        moving=false;
        if(left && !right){
            moving=true;
            x-=playerSpeed;
        }else if(right &&!left){
            moving=true;
            x+=playerSpeed;
        }
        if (up && !down) {
            moving=true;
            y-=playerSpeed;
        } else if (down && !up) {
            moving=true;
            y+=playerSpeed;
        }
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)x,(int)y,width,height,null);

    }



    private void setAnimation() {
        int startAni=playerAction;
        if(moving){
            playerAction=RUNNING;
        }else {
            playerAction=IDLE;
        }
        if(attacking){
            playerAction=ATTACK_1;
        }

        if(startAni!=playerAction){  // if at start playerAction is not same as last -> we need to reset the count
            resetAniTickCount();
        }
    }

    private void resetAniTickCount() {
        aniTick=0;
        aniIndex=0;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=getSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking=false;
            }

        }
    }

    private void loadAnimations() {

            BufferedImage bufferedImage= LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[9][6];

            for (int i = 0; i < animations.length; i++) {
                for (int j = 0; j < animations[i].length; j++) {
                    animations[i][j] = bufferedImage.getSubimage((j * 64), (i * 40), 64, 40);
                }
            }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void resetDirBooleans() {
        right=left=up=down=false;
    }
    public void setAttacking(boolean attacking){
        this.attacking=attacking;
    }
}
