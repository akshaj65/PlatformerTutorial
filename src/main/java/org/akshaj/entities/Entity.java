package org.akshaj.entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x ,y;
    protected int width ,height;
//    private Rectangle hitbox;  //A hitbox refers to an invisible area or shape defined around an object, character,
                                // or attack that detects collisions and interactions with other game elements
    protected Rectangle2D.Float hitbox; // we are using this to shrink the hitbox
    public Entity(float x,float y,int width,int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
//        initHitbox();
    }
//    protected void  updateHitbox(){
//        hitbox.x=(int) x;
//        hitbox.y=(int) y;
//    }
    protected  void drawHitbox(Graphics g){
        g.setColor(Color.green);
//        g.drawRect((int) x,(int) y,width,height);
        g.drawRect((int) hitbox.x,(int) hitbox.y,(int) hitbox.width,(int) hitbox.height);
    }
    protected void initHitbox(float x, float y,float width,float height) {
        hitbox=new Rectangle2D.Float((int) x,(int) y,width,height);
    }

//    private void initHitbox() {
//        hitbox=new Rectangle((int) x,(int) y,width,height);
//    }



}
