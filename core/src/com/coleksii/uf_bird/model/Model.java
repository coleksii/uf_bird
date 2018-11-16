package com.coleksii.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {
    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected Texture texture;
    protected float rightSide;
    protected float upperSide;
    protected float bottomSide;
    protected float leftSide;

    public Model() {
    }

    public Model(float x, float y, float width, float height, Texture texture, float rightSide, float upperSide, float bottomSide, float leftSide) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.rightSide = rightSide;
        this.upperSide = upperSide;
        this.bottomSide = bottomSide;
        this.leftSide = leftSide;
    }

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
