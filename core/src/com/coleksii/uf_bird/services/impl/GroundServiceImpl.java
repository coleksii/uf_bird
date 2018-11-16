package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.services.GroundService;

import java.util.LinkedList;
import java.util.List;

public class GroundServiceImpl implements GroundService {

    private static float speed = UserInformation.getGamespeed();

    @Override
    public List<Ground> createGroundList(){

        List<Ground> groundList;
        Ground groundFirst;
        Ground groundSecond;

        groundList = new LinkedList<Ground>();

        groundFirst = new Ground(TextureName.GROUND.getValue());
        groundFirst.setWidth(Gdx.graphics.getWidth() + 30);
        groundFirst.setHeight(Gdx.graphics.getHeight() / 10);
        groundFirst.setY(0);
        groundFirst.setX(0);
        groundFirst.setSpeed(speed);
        groundFirst.setColisianble(true);
        groundList.add(groundFirst);

        groundSecond = new Ground(TextureName.GROUND.getValue());
        groundSecond.setWidth(Gdx.graphics.getWidth() + 30);
        groundSecond.setHeight(Gdx.graphics.getHeight() / 10);
        groundSecond.setY(0);
        groundSecond.setX(Gdx.graphics.getWidth());
        groundSecond.setSpeed(speed);
        groundSecond.setColisianble(true);
        groundList.add(groundSecond);

        return groundList;
    }

    @Override
    public List<Ground> createBackGroundList() {
        List<Ground> groundList;
        Ground backGroundFirst;
        Ground backGroundSecond;

        groundList = new LinkedList<Ground>();
        backGroundFirst = new Ground(TextureName.BACK.getValue());
        backGroundFirst.setWidth(Gdx.graphics.getWidth());
        backGroundFirst.setHeight(Gdx.graphics.getHeight());
        backGroundFirst.setX(0);
        backGroundFirst.setY(0);
        backGroundFirst.setSpeed(speed / 2);
        groundList.add(backGroundFirst);

        backGroundSecond = new Ground(TextureName.BACK.getValue());
        backGroundSecond.setWidth(Gdx.graphics.getWidth());
        backGroundSecond.setHeight(Gdx.graphics.getHeight());
        backGroundSecond.setX(Gdx.graphics.getWidth());
        backGroundSecond.setY(0);
        backGroundSecond.setSpeed(speed / 2);
        groundList.add(backGroundSecond);

        return groundList;
    }


    @Override
    public void processingSpeed(List<Ground> grounds) {
        for (Ground model: grounds) {
            model.setX(model.getX() - model.getSpeed());
            if (model.getRightSide() <= 0) {
                model.setX(Gdx.graphics.getWidth() - 10);
            }
        }
    }
}
