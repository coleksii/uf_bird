package com.coleksii.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.coleksii.uf_bird.background.ScrolingBackground;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Bird bird;
	private ScrolingBackground scrolingBackground;
	private int speedPipes = 3;
	private int counterTime = 0;
	private List<PipePair> pipesCollection;
	private List<PipePair> storePipes;
	private PipesService pipesService;
	private int timeCreatePipes = 100;


	@Override
	public void create () {
		batch = new SpriteBatch();
		bird = new Bird(TextureName.BIRD.getValue());
		bird.setX(Gdx.graphics.getWidth()/ 2 / 2);
		bird.setY(Gdx.graphics.getHeight()/ 2);
		scrolingBackground = new ScrolingBackground();
		pipesCollection = new LinkedList<PipePair>();
		pipesService = new PiperServiceImpl();
		storePipes = cretateStorePipes();
	}

	private List<PipePair> cretateStorePipes() {
		int i = 0;
		List list = new LinkedList<PipePair>();
		while (i > 10){
			list.add(pipesService.generatePiperPair());
		}
		return list;
	}

	@Override
	public void render () {
		counterTime++;
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (collision()) {
			System.out.println("EXIT");
			Gdx.app.exit();
		}
		birdController();
		processingPipe();
		drawing();
	}

	private boolean collision() {
		return collisionWithObject();
	}

	private boolean collisionWithObject() {
		for (PipePair pipePair: pipesCollection){
			if (collisionWithPipe(pipePair.getDownerPipe()) || collisionWithPipe(pipePair.getUpperPipe()))
				return true;
		}
		return false;
	}

	private boolean collisionWithPipe(OnePipe pipe) {
		return bird.getRightSide() > pipe.getLeftSide() && bird.getLeftSide() < pipe.getRightSide() && bird.getUpperSide() > pipe.getBottomSide() && bird.getBottomSide() < pipe.getUpperSide();
	}

	private boolean collisionWithMap() {
		return	bird.getBottomSide() < 0 ||
				bird.getUpperSide() > Gdx.graphics.getHeight() ||
				bird.getLeftSide() < 0 ||
				bird.getRightSide() > Gdx.graphics.getWidth();
	}

	private void processingPipe() {
		if (counterTime % timeCreatePipes == 0){
			if (!storePipes.isEmpty()){
				pipesCollection.add(pipesService.changeCurrentPiperPair(storePipes.remove(0)));
			}else
				pipesCollection.add(pipesService.generatePiperPair());
		}
		for (PipePair pipes : pipesCollection){
			pipes.getDownerPipe().setX(pipes.getDownerPipe().getX() - speedPipes);
			pipes.getUpperPipe().setX(pipes.getUpperPipe().getX() - speedPipes);
			if (pipes.getDownerPipe().getRightSide() < 0) {
				storePipes.add(pipes);
			}
		}
		for (PipePair pipePair : storePipes){
			if (pipesCollection.contains(pipePair)){
				pipesCollection.remove(pipePair);
			}
		}
	}

	private void drawing() {
		OnePipe upperPipe;
		OnePipe downPipe;
		scrolingBackground.updateBackGround();
		batch.begin();
		batch.draw(bird.getTexture(), bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight());
		for (PipePair pipes : pipesCollection){
			upperPipe = pipes.getUpperPipe();
			downPipe = pipes.getDownerPipe();
			batch.draw(downPipe.getTexture(), downPipe.getX(), downPipe.getY(), downPipe.getWidth(), downPipe.getHeight());
			batch.draw(upperPipe.getTexture(), upperPipe.getX(), upperPipe.getY(), upperPipe.getWidth(), upperPipe.getHeight());
		}
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
	public void dispose () {
		batch.dispose();
	}
}
