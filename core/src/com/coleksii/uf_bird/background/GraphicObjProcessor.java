package com.coleksii.uf_bird.background;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.controller.Controller;
import com.coleksii.uf_bird.enums.State;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.BackGroundService;
import com.coleksii.uf_bird.services.DrawingServices;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.impl.BackGroundServiceImpl;
import com.coleksii.uf_bird.services.impl.DrawingServicesImpl;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;

import java.util.LinkedList;
import java.util.List;


public class GraphicObjProcessor {
    private Controller controller;
    private List<PipePair> pipesCollection;
    private List<PipePair> storePipes;
    private PipesService pipesService;
    private Stage stage;
    private Stage stageGameOver;
    private Bird bird;
    private DrawingServices drawingServices;
    private List<Ground> groundList;
    private BackGroundService backGroundService;

    public GraphicObjProcessor() {
        backGroundService = new BackGroundServiceImpl();
        groundList = backGroundService.createGroundList();
        controller = new Controller();
        bird = new Bird(TextureName.BIRD.getValue());
        pipesCollection = new LinkedList<PipePair>();
        pipesService = new PiperServiceImpl();
        storePipes = pipesService.createStorePipes();
        stage = new Stage();
        stageGameOver = new Stage();
        drawingServices = new DrawingServicesImpl(stage, stageGameOver);
    }

    public void processing(){
        if (UserInformation.getUserState() == State.PREPARE)
            processPrepareState();
        else if (UserInformation.getUserState() == State.GAME)
            processGameState();
        else if (UserInformation.getUserState() == State.MAIN_MENU) {
            returnToMainMenu();
        }
        if (UserInformation.getUserState() != State.GAME_OVER) {
            backGroundService.processingSpeed(groundList);
        }
        drawingServices.drawing(bird, pipesCollection, groundList);
    }

    private void processGameState() {
        if (collision()) {
            UserInformation.setUserState(State.GAME_OVER);
        } else {
            controller.birdController(bird);
            pipesService.processingPipe(pipesCollection, storePipes);
        }
    }

    private void returnToMainMenu() {
        bird.setY(Gdx.graphics.getHeight() / 2);
        bird.setX(Gdx.graphics.getWidth() / 4);
        UserInformation.setUserState(State.MAIN_MENU);
        Gdx.input.setInputProcessor(stage);
        storePipes.addAll(pipesCollection);
        pipesCollection.clear();
        UserInformation.setLatestGameScore(UserInformation.getGameScore());
        UserInformation.setGameScore(0);
    }

    private void processPrepareState() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
            UserInformation.setUserState(State.GAME);
            Gdx.input.setInputProcessor(null);
        }
    }


    private boolean collisionWithGround(){
        for (Ground ground : groundList){
            if (ground.isColisianble())
                if (bird.getBottomSide() < ground.getUpperSide())
                    return true;
        }
        return false;
    }

    private boolean collision() {
        return collisionWithObject() || collisionWithGround();
    }


    private boolean collisionWithObject() {
        for (PipePair pipePair : pipesCollection) {
            if (collisionWithPipe(pipePair.getDownerPipe()) || collisionWithPipe(pipePair.getUpperPipe()))
                return true;
            if (!pipePair.isPassed() && bird.getLeftSide() > pipePair.getDownerPipe().getRightSide()){
                UserInformation.setGameScore(UserInformation.getGameScore() + 1);
                pipePair.setPassed(true);
            }
        }
        return false;
    }

    private boolean collisionWithPipe(OnePipe pipe) {
        return bird.getRightSide() > pipe.getLeftSide() && bird.getLeftSide() < pipe.getRightSide() && bird.getUpperSide() > pipe.getBottomSide() && bird.getBottomSide() < pipe.getUpperSide();
    }
}
