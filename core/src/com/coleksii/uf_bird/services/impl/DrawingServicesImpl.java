package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.enums.States;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.Model;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.ButtonService;
import com.coleksii.uf_bird.services.DrawingServices;

import java.util.List;

public class DrawingServicesImpl implements DrawingServices {

    private SpriteBatch batch;
    private Stage stage;
    private float elapsedTime;
    private BitmapFont font;
    private BitmapFont prepareFont;
    private ButtonService buttonService;
    private Ground backGroundFirst;
    private Ground backGroundSecond;
    private Ground groundFirst;
    private Ground groundSecond;
    private int speedBackground = 3;
    private int speedGround = 3;

    public DrawingServicesImpl(UserInformation userInformation) {
        batch = new SpriteBatch();
        stage = new Stage();

        font = new BitmapFont();
        font.getData().setScale(2);

        prepareFont = new BitmapFont();
        prepareFont.setColor(Color.BLACK);
        prepareFont.getData().setScale(1.5f);

        buttonService = new ButtonServiceImpl();
        stage.addActor(buttonService.createNewGameButton(userInformation));
        stage.addActor(buttonService.createExitButton());

        createGround();
    }

    @Override
    public void drawing(UserInformation userInformation, Bird bird, List<PipePair> pipesCollection) {
        OnePipe upperPipe;
        OnePipe downPipe;
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        updateBackGround();
        batch.draw(bird.getAnimation().getKeyFrame(elapsedTime, true), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        for (PipePair pipes : pipesCollection) {
            upperPipe = pipes.getUpperPipe();
            downPipe = pipes.getDownerPipe();
            batch.draw(downPipe.getTexture(), downPipe.getX(), downPipe.getY(), downPipe.getWidth(), downPipe.getHeight());
            batch.draw(upperPipe.getTexture(), upperPipe.getX(), upperPipe.getY(), upperPipe.getWidth(), upperPipe.getHeight());
        }
        if (userInformation.getUserState() == States.PREPARE) {
            prepareFont.draw(batch, "TOUCH TO PLAY", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }
        if (userInformation.getUserState() == States.MAIN_MENU && userInformation.getLatestScore() != 0) {
            prepareFont.draw(batch, "YOUR LATEST SCORE IS: " + userInformation.getLatestScore(), Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }
        font.draw(batch, "score: " + userInformation.getScore(), 10, Gdx.graphics.getHeight() - font.getCapHeight());
        batch.end();
        if (userInformation.getUserState() == States.MAIN_MENU) {
            stage.draw();
        }
    }

    private void updateBackGround(){
        drawObject(backGroundFirst, speedBackground);
        drawObject(backGroundSecond, speedBackground);
        drawObject(groundFirst, speedGround);
        drawObject(groundSecond, speedGround);
    }

    private void drawObject(Model model, int speed) {
        batch.draw(model.getTexture(), model.getX(), model.getY(), model.getWidth(), model.getHeight());
        processingSpeed(model, speed);
    }

    private void processingSpeed(Model model, int speed) {
        model.setX(model.getX() - speed);
        if (model.getRightSide() <= 0){
            model.setX(Gdx.graphics.getWidth());
        }
    }

    private void createGround() {

        backGroundFirst = new Ground(TextureName.BACK.getValue());
        backGroundFirst.setWidth(Gdx.graphics.getWidth());
        backGroundFirst.setHeight(Gdx.graphics.getHeight());
        backGroundFirst.setX(0);
        backGroundFirst.setY(0);

        backGroundSecond = new Ground(TextureName.BACK.getValue());
        backGroundSecond.setWidth(Gdx.graphics.getWidth());
        backGroundSecond.setHeight(Gdx.graphics.getHeight());
        backGroundSecond.setX(Gdx.graphics.getWidth());
        backGroundSecond.setY(0);

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
}
