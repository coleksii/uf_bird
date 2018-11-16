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
import com.coleksii.uf_bird.services.CollisionAndScoreService;
import com.coleksii.uf_bird.services.GroundService;
import com.coleksii.uf_bird.services.DrawingServices;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.impl.CollisionAndScoreServiceImpl;
import com.coleksii.uf_bird.services.impl.GroundServiceImpl;
import com.coleksii.uf_bird.services.impl.DrawingServicesImpl;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;

import java.util.LinkedList;
import java.util.List;


public class GameProcessor {
    private Controller controller;
    private List<PipePair> pipesCollection;
    private List<PipePair> storePipes;
    private PipesService pipesService;
    private Stage stage;
    private Stage stageGameOver;
    private Bird bird;
    private DrawingServices drawingServices;
    private List<Ground> groundList;
    private List<Ground> backGroundList;
    private GroundService groundService;
    private CollisionAndScoreService collisionAndScoreService;

    public GameProcessor() {
        collisionAndScoreService = new CollisionAndScoreServiceImpl();
        groundService = new GroundServiceImpl();
        groundList = groundService.createGroundList();
        backGroundList = groundService.createBackGroundList();
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
            groundService.processingSpeed(groundList);
            groundService.processingSpeed(backGroundList);
        }
        drawingServices.drawing(bird, pipesCollection, groundList, backGroundList);
    }

    private void upperGameLevel() {
        UserInformation.setLevel(UserInformation.getLevel() + 1);

        for (Ground ground: groundList){
            ground.setSpeed(ground.getSpeed() * 1.2f);
        }
        UserInformation.setGamespeed(UserInformation.getGamespeed() * 1.2f);
        UserInformation.setCreatePipes((long) (UserInformation.getCreatePipes() / 1.2));
        UserInformation.setBirdSpeed(UserInformation.getBirdSpeed() * 1.05f);
    }

    private void processGameState() {
//        if (collision()) {
        if (collisionAndScoreService.collisionAndScoreProcess(bird, pipesCollection, groundList)) {
            UserInformation.setUserState(State.GAME_OVER);
        } else {
            controller.birdController(bird);
            pipesService.processingPipe(pipesCollection, storePipes);
        }
    }

    private void returnToMainMenu() {
        bird.setY(Gdx.graphics.getHeight() / 2);
        bird.setX(Gdx.graphics.getWidth() / 4);
        UserInformation.toDefault();
        Gdx.input.setInputProcessor(stage);
        storePipes.addAll(pipesCollection);
        pipesCollection.clear();
        for (Ground ground: groundList){
            ground.setSpeed(3);
        }
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
        return collisionWithObjectAndProcessScore() || collisionWithGround() || collisionWithMap();
    }

    private boolean collisionWithMap() {
        return bird.getLeftSide() < 0 || bird.getRightSide() > Gdx.graphics.getWidth() || bird.getUpperSide() > Gdx.graphics.getHeight() || bird.getBottomSide() < 0;
    }


    private boolean collisionWithObjectAndProcessScore() {
        for (PipePair pipePair : pipesCollection) {
            if (collisionWithPipe(pipePair.getDownerPipe()) || collisionWithPipe(pipePair.getUpperPipe()))
                return true;
            if (!pipePair.isPassed() && bird.getLeftSide() > pipePair.getDownerPipe().getRightSide()){
                UserInformation.setGameScore(UserInformation.getGameScore() + 1);
                if (UserInformation.getGameScore() % 10 == 0){
                    upperGameLevel();
                }
                pipePair.setPassed(true);
            }
        }
        return false;
    }

    private boolean collisionWithPipe(OnePipe pipe) {
        return bird.getRightSide() > pipe.getLeftSide() && bird.getLeftSide() < pipe.getRightSide() && bird.getUpperSide() > pipe.getBottomSide() && bird.getBottomSide() < pipe.getUpperSide();
    }
}
