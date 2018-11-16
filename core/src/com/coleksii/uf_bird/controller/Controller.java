package com.coleksii.uf_bird.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;

public class Controller {

    public void birdController(Bird bird){
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            bird.setY(bird.getY() + UserInformation.getBirdSpeed());
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            bird.setY(bird.getY() - UserInformation.getBirdSpeed());
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bird.setX(bird.getX() - UserInformation.getBirdSpeed());
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bird.setX(bird.getX() + UserInformation.getBirdSpeed());
        else if (Gdx.input.isTouched())
            bird.setY(bird.getY() + UserInformation.getBirdSpeed());
        else
            bird.setY(bird.getY() - UserInformation.getBirdSpeed());
    }
}
