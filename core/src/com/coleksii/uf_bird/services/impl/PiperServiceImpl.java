package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.TimeService;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PiperServiceImpl implements PipesService {

    private int size;
    float spaceBetweenPipe;
    private int speedPipes;
    private TimeService timeService;

    public PiperServiceImpl(int pipeSpeed) {
        size =  Gdx.graphics.getHeight() / 10;
        spaceBetweenPipe = size * 4;
        this.speedPipes = pipeSpeed;
        this.timeService = new TimeServiceImpl();
    }

    public int getPipeSpeed() {
        return speedPipes;
    }

    @Override
    public PipePair generatePiperPair() {
        PipePair pipePair = new PipePair();
        float heightDown = generateFirstPosition(pipePair);
        pipePair.getDownerPipe().setY(heightDown);
        pipePair.getUpperPipe().setY(generateUpPoistion(pipePair));
        pipePair.getUpperPipe().setX(Gdx.graphics.getWidth() + 50);
        pipePair.getDownerPipe().setX(Gdx.graphics.getWidth() + 50);
        return pipePair;
    }

    @Override
    public PipePair changeCurrentPiperPair(PipePair pipePair) {
        size =  Gdx.graphics.getHeight() / 10;
        spaceBetweenPipe = size * 4;
        float heightDown = generateFirstPosition(pipePair);
        pipePair.getDownerPipe().setY(heightDown);
        pipePair.getUpperPipe().setY(generateUpPoistion(pipePair));
        pipePair.getUpperPipe().setX(Gdx.graphics.getWidth() + 50);
        pipePair.getDownerPipe().setX(Gdx.graphics.getWidth() + 50);
        return pipePair;
    }

    private float generateFirstPosition(PipePair pipePair) {
        float min = -pipePair.getDownerPipe().getHeight() + size;
        float max = Gdx.graphics.getHeight() - pipePair.getDownerPipe().getHeight() - size * 3;
        Random random = new Random();
        float position = min + random.nextFloat() * (max - min);;
        return position;
    }

    private float generateUpPoistion(PipePair pipePair){
        float min = pipePair.getDownerPipe().getY() + pipePair.getDownerPipe().getHeight() + spaceBetweenPipe;
        float max = Gdx.graphics.getHeight() - size;
        Random random = new Random();
        float position = min + random.nextFloat() * (max - min);;
        return position;
    }

    @Override
    public List<PipePair> createStorePipes() {
        List<PipePair> list = new LinkedList<PipePair>();
        while (list.size() < 10) {
            list.add(generatePiperPair());
        }
        return list;
    }

    @Override
    public void processingPipe(List<PipePair> pipesCollection, List<PipePair> storePipes) {
        if (timeService.isTimeTocreatePipe()) {
            if (!storePipes.isEmpty()) {
                pipesCollection.add(changeCurrentPiperPair(storePipes.remove(0)));
            } else
                pipesCollection.add(generatePiperPair());
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

}
