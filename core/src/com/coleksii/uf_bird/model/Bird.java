package com.coleksii.uf_bird.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class Bird extends Model{

    private Animation animation;

    public Bird(String value) {
        super(value);
        int size = calculateBirdSize();
        setWidth(size * 1.2f);
        setHeight(size);

        Texture textures[] = new Texture[4];
        textures[0] = new Texture("anim/frame-1.png");
        textures[1] = new Texture("anim/frame-2.png");
        textures[2] = new Texture("anim/frame-3.png");
        textures[3] = new Texture("anim/frame-4.png");

        animation = new Animation(1f/4f, textures);

    }

    private int calculateBirdSize(){
        int shortesSide = Gdx.graphics.getWidth() > Gdx.graphics.getHeight() ? Gdx.graphics.getHeight() : Gdx.graphics.getWidth();
        return shortesSide / 15;
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

    public Animation getAnimation() {
        return animation;
    }
}
