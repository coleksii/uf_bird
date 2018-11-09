package com.coleksii.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.background.GraphicObjProcessor;
import com.coleksii.uf_bird.enums.States;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.ButtonService;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.TimeService;
import com.coleksii.uf_bird.services.impl.ButtonServiceImpl;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;
import com.coleksii.uf_bird.services.impl.TimeServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private Bird bird;
    private GraphicObjProcessor graphicObjProcessor;
    private int speedPipes = 3;
    private List<PipePair> pipesCollection;
    private List<PipePair> storePipes;
    private PipesService pipesService;
    private TimeService timeService;
    private float elapsedTime;
    private BitmapFont font;
    private BitmapFont prepareFont;
    private long score;
    private long latestScore;
    private States states;
    private Stage stage;
    private ButtonService buttonService;

    public void setState(States state) {
        this.states = state;
    }


    @Override
    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        bird = new Bird(TextureName.BIRD.getValue());
        graphicObjProcessor = new GraphicObjProcessor();
        pipesCollection = new LinkedList<PipePair>();
        pipesService = new PiperServiceImpl();
        storePipes = pipesService.createStorePipes();
        timeService = new TimeServiceImpl();
        font = new BitmapFont();
        font.getData().setScale(2);

        prepareFont = new BitmapFont();
        prepareFont.setColor(Color.BLACK);
        prepareFont.getData().setScale(1.5f);

        states = States.MAIN_MENU;
        buttonService = new ButtonServiceImpl(this);
        stage.addActor(buttonService.createNewGameButton());
        stage.addActor(buttonService.createExitButton());
    }

    @Override
    public void render() {
        elapsedTime += Gdx.graphics.getDeltaTime();
        if (states == States.PREPARE) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY) || Gdx.input.isTouched()) {
                states = States.GAME;
                Gdx.input.setInputProcessor(null);
            }
        } else if (states == States.GAME) {
            score++;
            if (collision() || graphicObjProcessor.collisionWithGround(bird)) {
                bird.setY(Gdx.graphics.getHeight() / 2);
                states = States.MAIN_MENU;
                Gdx.input.setInputProcessor(stage);
                storePipes.addAll(pipesCollection);
                pipesCollection.clear();
                latestScore = score;
                score = 0;
            } else {
                birdController();
                processingPipe();
            }
        }
        drawing();
    }

    private boolean collision() {
        return collisionWithObject();
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

    private boolean collisionWithMap() {
        return bird.getBottomSide() < 0 ||
                bird.getUpperSide() > Gdx.graphics.getHeight() ||
                bird.getLeftSide() < 0 ||
                bird.getRightSide() > Gdx.graphics.getWidth();
    }

    private void processingPipe() {
        if (timeService.isTimeTocreatePipe()) {
            if (!storePipes.isEmpty()) {
                pipesCollection.add(pipesService.changeCurrentPiperPair(storePipes.remove(0)));
            } else
                pipesCollection.add(pipesService.generatePiperPair());
        }
        for (PipePair pipes : pipesCollection) {
            pipes.getDownerPipe().setX(pipes.getDownerPipe().getX() - speedPipes);
            pipes.getUpperPipe().setX(pipes.getUpperPipe().getX() - speedPipes);
            if (pipes.getDownerPipe().getRightSide() < 0) {
                storePipes.add(pipes);
            }
        }
        for (PipePair pipePair : storePipes) {
            if (pipesCollection.contains(pipePair)) {
                pipesCollection.remove(pipePair);
            }
        }
    }

    private void drawing() {
        OnePipe upperPipe;
        OnePipe downPipe;
        graphicObjProcessor.updateBackGround();
        batch.begin();
        if (states == States.MAIN_MENU) {
            stage.draw();
        }
        batch.end();

        batch.begin();
        batch.draw((Texture) bird.getAnimation().getKeyFrame(elapsedTime, true), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        for (PipePair pipes : pipesCollection) {
            upperPipe = pipes.getUpperPipe();
            downPipe = pipes.getDownerPipe();
            batch.draw(downPipe.getTexture(), downPipe.getX(), downPipe.getY(), downPipe.getWidth(), downPipe.getHeight());
            batch.draw(upperPipe.getTexture(), upperPipe.getX(), upperPipe.getY(), upperPipe.getWidth(), upperPipe.getHeight());
        }
        if (states == States.PREPARE) {
            prepareFont.draw(batch, "TOUCH TO PLAY", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }
        if (states == States.MAIN_MENU && latestScore != 0)
            prepareFont.draw(batch, "YOUR LATEST SCORE IS: " + latestScore, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        font.draw(batch, "score: " + score, 10, Gdx.graphics.getHeight() - font.getCapHeight());
        batch.end();
    }

    private void birdController() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            bird.setY(bird.getY() + 5);
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            bird.setY(bird.getY() - 5);
        else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            bird.setX(bird.getX() - 5);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            bird.setX(bird.getX() + 5);

        else if (Gdx.input.isTouched())
            bird.setY(bird.getY() + 5);
        else
            bird.setY(bird.getY() - 5);

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        for (PipePair pipePair : storePipes) {
            pipePair.getDownerPipe().getTexture().dispose();
            pipePair.getUpperPipe().getTexture().dispose();
        }

        for (PipePair pipePair : pipesCollection) {
            pipePair.getDownerPipe().getTexture().dispose();
            pipePair.getUpperPipe().getTexture().dispose();
        }

    }
}
