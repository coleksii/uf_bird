package com.coleksii.uf_bird.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coleksii.uf_bird.enums.TextureName;

public class ScrolingBackground {

    private Texture image;
    private float x1, x2;
    private int speed;//pixels per second
    private SpriteBatch batch;
    private int width;

    public ScrolingBackground() {
        this.image = new Texture(TextureName.BACK.getValue());
        x1 = 0;
        x2 = Gdx.graphics.getWidth();
        width = Gdx.graphics.getWidth();
        batch = new SpriteBatch();
        speed = 2;
    }

    public void updateBackGround(){
        calculation();
        batch.begin();
        batch.draw(image, x1 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(image, x2 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    private void calculation() {
        x1 -= speed;
        x2 -= speed;
        if (x1 + width <= 0){
            x1 = width;
        }
        if (x2 + width <= 0){
            x2 = width;
        }
    }
}
