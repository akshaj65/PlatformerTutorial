package org.akshaj.entities;

import org.akshaj.main.Game;
import static  org.akshaj.utils.HelperMethods.*;
import org.akshaj.utils.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import static org.akshaj.utils.Constants.PlayerConstants.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex,aniSpeed=15;
    private int playerAction=IDLE;
    private boolean left,right,up,down;
    private float playerSpeed =2.0f;
    private boolean moving=false,attacking=false;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;  //21 and 4 are the points of the top left edge of the player sprite
                                                 // can check in gimp for pointer
    private float yDrawOffset = 4 * Game.SCALE;

    // jumping /Gravity
    private float airSpeed = 0f;
    private float gravity =0.04f * Game.SCALE;
    private  float jumpSpeed = -2.25f * Game.SCALE;
    private float  fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;
    private boolean jump = false;

    public Player(float x,float y,int width,int height){
        super(x,y,width,height);
        loadAnimations();
        initHitbox(x,y,20 * Game.SCALE,27 * Game.SCALE); // height 28-1 as want no gap b/w floor and player
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    private void updatePos() {
        moving=false;
        if(jump){
           jump();
        }
        if(!left&&!right&&!inAir){
            return;
        }
        float xSpeed =0;
        if(left)
            xSpeed-=playerSpeed;
        if(right)
            xSpeed+=playerSpeed;

        //checking if not in air and also not on floor
        if(!inAir){
            if(!isEntityOnFloor(hitbox,lvlData)){
                inAir=true;
            }
        }

        if(inAir){ //if in air and we want to move
            if(canMoveHere(hitbox.x,hitbox.y+airSpeed, hitbox.width, hitbox.height, lvlData)){ // when we can move
                hitbox.y+=airSpeed;
                airSpeed+=gravity;
                updateXPos(xSpeed);
            }else { // if, can't move
              hitbox.y=getEntityYPosUnderRoofOrAboveFloor(hitbox,airSpeed);
              if(airSpeed>0) // we hit the floor
                  resetInAir();
              else           //we hit the roof
                  airSpeed=fallSpeedAfterCollision;
              updateXPos(xSpeed);
            }
        }else //if not in air
            updateXPos(xSpeed);

        moving=true;

    }



    private void jump() {
        if(inAir)
            return;
        inAir=true;
        airSpeed=jumpSpeed;
    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }


    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x+xSpeed,hitbox.y, hitbox.width, hitbox.height, lvlData)){
            this.hitbox.x+=xSpeed;
        }else{
            hitbox.x= getEntityXPosNextToWall(hitbox,xSpeed);
        }
    }


    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex],(int)(hitbox.x - xDrawOffset),(int)(hitbox.y -yDrawOffset),width,height,null);
        drawHitbox(g);
    }



    private void setAnimation() {
        int startAni=playerAction;
        if(moving){
            playerAction=RUNNING;
        }else {
            playerAction=IDLE;
        }
        if(inAir){
          if(airSpeed<0){
              playerAction=JUMP;
          }else {
              playerAction=FALLING;
          }
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

    public  void loadLevelData(int[][] lvlData){
        this.lvlData=lvlData;
        if (!isEntityOnFloor(hitbox,lvlData)){ //check at start if the player is on air
            inAir=true;
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

    public void setJump(boolean b) {
        jump=b;
    }
}
