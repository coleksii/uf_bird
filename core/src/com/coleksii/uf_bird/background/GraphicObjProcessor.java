package com.coleksii.uf_bird.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.model.BackGround;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.Model;

public class GraphicObjProcessor {

    private int speedBackground;//pixels per second
    private int speedGround;
    private SpriteBatch batch;
    private Ground groundFirst;
    private Ground groundSecond;
    private BackGround backGroundFirst;
    private BackGround backGroundSecond;

    public GraphicObjProcessor() {
        createBackGround();
        createGround();

        batch = new SpriteBatch();
        speedBackground = 2;
        speedGround = 3;
    }

    private void createBackGround() {
        backGroundFirst = new BackGround(TextureName.BACK.getValue());
        backGroundFirst.setWidth(Gdx.graphics.getWidth());
        backGroundFirst.setHeight(Gdx.graphics.getHeight());
        backGroundFirst.setX(0);
        backGroundFirst.setY(0);

        backGroundSecond = new BackGround(TextureName.BACK.getValue());
        backGroundSecond.setWidth(Gdx.graphics.getWidth());
        backGroundSecond.setHeight(Gdx.graphics.getHeight());
        backGroundSecond.setX(Gdx.graphics.getWidth());
        backGroundSecond.setY(0);
    }

    private void createGround() {
        groundFirst = new Ground(TextureName.GROUND.getValue());
        groundFirst.setWidth(Gdx.graphics.getWidth() + 20);
        groundFirst.setHeight(Gdx.graphics.getHeight() / 10);
        groundFirst.setY(0);
        groundFirst.setX(0);

        groundSecond = new Ground(TextureName.GROUND.getValue());
        groundSecond.setWidth(Gdx.graphics.getWidth() + 20);
        groundSecond.setHeight(Gdx.graphics.getHeight() / 10);
        groundSecond.setY(0);
        groundSecond.setX(Gdx.graphics.getWidth());
    }

    public void updateBackGround(){
        batch.begin();

        batch.draw(backGroundFirst.getTexture(), backGroundFirst.getX() , backGroundFirst.getY(), backGroundFirst.getWidth(), backGroundFirst.getHeight());
        calculation(backGroundFirst, speedBackground);
        batch.draw(backGroundSecond.getTexture(), backGroundSecond.getX() , backGroundSecond.getY(), backGroundSecond.getWidth(), backGroundSecond.getHeight());
        calculation(backGroundSecond, speedBackground);

        batch.draw(groundFirst.getTexture(), groundFirst.getX(), groundFirst.getY(), groundFirst.getWidth(), groundFirst.getHeight());
        calculation(groundFirst, speedGround);
        batch.draw(groundSecond.getTexture(), groundSecond.getX(), groundSecond.getY(), groundSecond.getWidth(), groundSecond.getHeight());
        calculation(groundSecond, speedGround);
        batch.end();
    }

    public boolean collisionWithGround(Bird bird){
        return bird.getBottomSide() < groundSecond.getUpperSide();
    }

    private void calculation(Model model, int speed) {
        model.setX(model.getX() - speed);
        if (model.getRightSide() <= 0){
            model.setX(Gdx.graphics.getWidth());
        }
    }
}
