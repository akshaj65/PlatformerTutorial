package org.akshaj.entities;

public abstract class Entity {
    protected float x ,y;  //only Subclasses and Classes in the same package can access it
    public Entity(float x,float y){
        this.x=x;
        this.y=y;
    }
}
