package com.coleksii.uf_bird.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.background.GraphicObjProcessor;
import com.coleksii.uf_bird.enums.States;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.TimeService;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;
import com.coleksii.uf_bird.services.impl.TimeServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class GameEngine {
    private static volatile GameEngine instance;

    public static GameEngine getInstance() {
        GameEngine localInstance = instance;
        if (localInstance == null) {
            synchronized (GameEngine.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GameEngine();
                }
            }
        }
        return localInstance;
    }

    private SpriteBatch batch;
    private Bird bird;
    private GraphicObjProcessor graphicObjProcessor;
    private int speedPipes = 3;
    private TimeService timeService;
    private States states;
    private Stage stage;
    private long score;
    private float elapsedTime;
    private List<PipePair> pipesCollection;
    private List<PipePair>  storePipes;
    private PipesService pipesService;


    private GameEngine(){
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        bird = new Bird(TextureName.BIRD.getValue());
        bird.setX(Gdx.graphics.getWidth() / 2 / 2);
        bird.setY(Gdx.graphics.getHeight() / 2);

        graphicObjProcessor = new GraphicObjProcessor();
        pipesCollection = new LinkedList<PipePair>();

        storePipes = pipesService.createStorePipes();
        pipesService = new PiperServiceImpl();
        timeService = new TimeServiceImpl();
        states = States.MAIN_MENU;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Bird getBird() {
        return bird;
    }

    public States getStates() {
        return states;
    }

    public Stage getStage() {
        return stage;
    }

    public long getScore() {
        return score;
    }

    public List<PipePair> getPipesCollection() {
        return pipesCollection;
    }

    public List<PipePair> getStorePipes() {
        return storePipes;
    }
}
