package com.coleksii.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Model {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected Texture texture;
    protected float rightSide;
    protected float upperSide;
    protected float bottomSide;
    protected float leftSide;


    Model(String value) {
        this.texture = new Texture(value);
    }

    public void setX(float x) {
        rightSide = x + width;
        leftSide = x;
        this.x = x;
    }

    public void setY(float y) {
        upperSide = y + height;
        bottomSide = y;
        this.y = y;
    }
}
