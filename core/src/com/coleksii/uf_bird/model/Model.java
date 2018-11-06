package com.coleksii.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;

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

    public Model(String value) {
        this.texture = new Texture(value);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        rightSide = x + width;
        leftSide = x;
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        upperSide = y + height;
        bottomSide = y;
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public float getRightSide() {
        return rightSide;
    }

    public void setRightSide(float rightSide) {
        this.rightSide = rightSide;
    }

    public float getUpperSide() {
        return upperSide;
    }

    public void setUpperSide(float upperSide) {
        this.upperSide = upperSide;
    }

    public float getBottomSide() {
        return bottomSide;
    }

    public void setBottomSide(float bottomSide) {
        this.bottomSide = bottomSide;
    }

    public float getLeftSide() {
        return leftSide;
    }

    public void setLeftSide(float leftSide) {
        this.leftSide = leftSide;
    }
}
