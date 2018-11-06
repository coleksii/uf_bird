package com.coleksii.uf_bird.model;

import com.badlogic.gdx.Gdx;

public class Bird extends Model{

    public Bird(String value) {
        super(value);
        int size = calculateBirdSize();
        setWidth(size * 1.2f);
        setHeight(size);
    }

    private int calculateBirdSize(){
        int shortesSide = Gdx.graphics.getWidth() > Gdx.graphics.getHeight() ? Gdx.graphics.getHeight() : Gdx.graphics.getWidth();
        return shortesSide / 20;
    }

    @Override
    public void setY(float y) {
        if (y > 0 && y + height < Gdx.graphics.getHeight()) {
            upperSide = y + height;
            bottomSide = y;
            this.y = y;
        }
    }

    @Override
    public void setX(float x) {
        if (x > 0 && x + width < Gdx.graphics.getWidth()) {
            rightSide = x + width;
            leftSide = x;
            this.x = x;
        }
    }
}
