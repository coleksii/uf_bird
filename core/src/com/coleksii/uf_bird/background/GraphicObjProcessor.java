package com.coleksii.uf_bird.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.controller.Controller;
import com.coleksii.uf_bird.enums.States;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.BackGround;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.Model;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.ButtonService;
import com.coleksii.uf_bird.services.DrawingServices;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.impl.ButtonServiceImpl;
import com.coleksii.uf_bird.services.impl.DrawingServicesImpl;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;

import java.util.LinkedList;
import java.util.List;


public class GraphicObjProcessor {

    private int speedBackground;
    private int speedGround;
    private SpriteBatch batch;
    private Ground groundFirst;
    private Ground groundSecond;
    private BackGround backGroundFirst;
    private BackGround backGroundSecond;
    private Controller controller;


    private List<PipePair> pipesCollection;
    private List<PipePair> storePipes;
    private PipesService pipesService;
    private float elapsedTime;
    private BitmapFont font;
    private BitmapFont prepareFont;
//    private long score;
//    private long latestScore;
    private Stage stage;
    private ButtonService buttonService;
    private Bird bird;
    private UserInformation userInformation;
    private DrawingServices drawingServices;

    public GraphicObjProcessor() {
        createBackGround();
        createGround();
        batch = new SpriteBatch();
        speedBackground = 2;
        speedGround = 3;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        bird = new Bird(TextureName.BIRD.getValue());
        pipesCollection = new LinkedList<PipePair>();

        controller = new Controller();
        pipesService = new PiperServiceImpl(3);
        storePipes = pipesService.createStorePipes();

        font = new BitmapFont();
        font.getData().setScale(2);

        prepareFont = new BitmapFont();
        prepareFont.setColor(Color.BLACK);
        prepareFont.getData().setScale(1.5f);

        userInformation = new UserInformation(States.MAIN_MENU);
        drawingServices = new DrawingServicesImpl(userInformation);

        buttonService = new ButtonServiceImpl();
        stage.addActor(buttonService.createNewGameButton(userInformation));
        stage.addActor(buttonService.createExitButton());
    }

    public void processing(){
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (userInformation.getUserState() == States.PREPARE)
            processPrepareState();
        else if (userInformation.getUserState() == States.GAME)
            processGameState();
        drawingServices.drawing(userInformation, bird, pipesCollection);
//        drawing();
    }

    private void processGameState() {
        userInformation.setScore(userInformation.getScore() + 1);
        if (collision()) {
            returnToMainMenu();
        } else {
            controller.birdController(bird);
            pipesService.processingPipe(pipesCollection, storePipes);
        }
    }

    private void returnToMainMenu() {
        bird.setY(Gdx.graphics.getHeight() / 2);
        userInformation.setUserState(States.MAIN_MENU);
        Gdx.input.setInputProcessor(stage);
        storePipes.addAll(pipesCollection);
        pipesCollection.clear();
        userInformation.setLatestScore(userInformation.getScore());
        userInformation.setScore(0);
    }

    private void processPrepareState() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
            userInformation.setUserState(States.GAME);
            Gdx.input.setInputProcessor(null);
        }
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

    public boolean collisionWithGround(){
        return bird.getBottomSide() < groundSecond.getUpperSide();
    }

    private boolean collision() {
        return collisionWithObject() || collisionWithGround();
    }


    private boolean collisionWithObject() {
        for (PipePair pipePair : pipesCollection) {
            if (collisionWithPipe(pipePair.getDownerPipe()) || collisionWithPipe(pipePair.getUpperPipe()))
                return true;
        }
        return false;
    }

    private boolean collisionWithPipe(OnePipe pipe) {
        return bird.getRightSide() > pipe.getLeftSide() && bird.getLeftSide() < pipe.getRightSide() && bird.getUpperSide() > pipe.getBottomSide() && bird.getBottomSide() < pipe.getUpperSide();
    }

    private void drawing() {
        OnePipe upperPipe;
        OnePipe downPipe;

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

    public void updateBackGround(){
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
}
