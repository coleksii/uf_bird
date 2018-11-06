package com.coleksii.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class OnePipe extends Model{

    private float relativeHeight;
    private float relativeWidth;

    public OnePipe(String value) {
        super(value);
        width = Gdx.graphics.getWidth() * 4 / 25;
        height = Gdx.graphics.getHeight() * 4 / 5;
        relativeHeight = 0;
        relativeWidth = 0;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void setX(float x){
        rightSide = x + width - relativeWidth;
        leftSide = x + relativeWidth;
        this.x = x;
    }

    @Override
    public void setY(float y){
        upperSide = y + height - relativeHeight;
        bottomSide = y + relativeHeight;
        this.y = y;
    }
}
