package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.enums.State;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.ButtonService;
import com.coleksii.uf_bird.services.DrawingServices;

import java.util.List;

public class DrawingServicesImpl implements DrawingServices {

    private SpriteBatch batch;
    private Stage stage;
    private Stage stageGameOver;
    private float elapsedTime;
    private BitmapFont font;
    private BitmapFont prepareFont;
    private ButtonService buttonService;
    private Texture gameover;


    public DrawingServicesImpl(Stage stage, Stage stageGameOver) {
        gameover = new Texture(TextureName.GAMEOVER.getValue());
        batch = new SpriteBatch();
        this.stage = stage;
        this.stageGameOver = stageGameOver;

        font = new BitmapFont();
        font.getData().setScale(2);

        prepareFont = new BitmapFont();
        prepareFont.setColor(Color.BLACK);
        prepareFont.getData().setScale(1.5f);

        buttonService = new ButtonServiceImpl();
        this.stage.addActor(buttonService.createNewGameButton());
        this.stage.addActor(buttonService.createExitButton());

        this.stageGameOver.addActor(buttonService.createToMainMenuButton());
    }

    @Override
    public void drawing(Bird bird, List<PipePair> pipesCollection, List<Ground> groundList) {
        OnePipe upperPipe;
        OnePipe downPipe;
        elapsedTime += Gdx.graphics.getDeltaTime();

        batch.begin();
        updateBackGround(groundList);
        batch.draw(bird.getAnimation().getKeyFrame(elapsedTime, true), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
        for (PipePair pipes : pipesCollection) {
            upperPipe = pipes.getUpperPipe();
            downPipe = pipes.getDownerPipe();
            batch.draw(downPipe.getTexture(), downPipe.getX(), downPipe.getY(), downPipe.getWidth(), downPipe.getHeight());
            batch.draw(upperPipe.getTexture(), upperPipe.getX(), upperPipe.getY(), upperPipe.getWidth(), upperPipe.getHeight());
        }
        if (UserInformation.getUserState() == State.PREPARE) {
            prepareFont.draw(batch, "TOUCH TO PLAY", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        }
        font.draw(batch, "score: " + UserInformation.getGameScore(), 10, Gdx.graphics.getHeight() - font.getCapHeight());
        if (UserInformation.getUserState() == State.GAME_OVER){
            batch.draw(gameover, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 4 / 2, Gdx.graphics.getHeight() / 4 * 3, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight()/ 10);
            prepareFont.draw(batch, "YOUR LATEST SCORE IS: " + UserInformation.getGameScore(), Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 5 * 2);
        }
        batch.end();
        if (UserInformation.getUserState() == State.MAIN_MENU) {
            Gdx.input.setInputProcessor(this.stage);
            stage.draw();
        }
        if (UserInformation.getUserState() == State.GAME_OVER) {
            Gdx.input.setInputProcessor(stageGameOver);
            stageGameOver.draw();
        }
    }

    private void updateBackGround(List<Ground> groundList) {
        for (Ground ground : groundList) {
            drawObject(ground);
        }
    }

    private void drawObject(Ground model) {
        batch.draw(model.getTexture(), model.getX(), model.getY(), model.getWidth(), model.getHeight());
    }
}
