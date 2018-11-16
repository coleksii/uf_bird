package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.CollisionAndScoreService;

import java.util.List;

public class CollisionAndScoreServiceImpl implements CollisionAndScoreService {
    @Override
    public boolean collisionAndScoreProcess(Bird bird, List<PipePair> pipesCollection, List<Ground> groundList) {
        return collisionWithObjectAndProcessScore(bird, pipesCollection, groundList) || collisionWithGround(bird, groundList) || collisionWithMap(bird);
    }

    private boolean collisionWithGround(Bird bird, List<Ground> groundList){
        for (Ground ground : groundList){
            if (ground.isColisianble())
                if (bird.getBottomSide() < ground.getUpperSide())
                    return true;
        }
        return false;
    }

    private boolean collisionWithMap(Bird bird) {
        return bird.getLeftSide() < 0 || bird.getRightSide() > Gdx.graphics.getWidth() || bird.getUpperSide() > Gdx.graphics.getHeight() || bird.getBottomSide() < 0;
    }


    private boolean collisionWithObjectAndProcessScore(Bird bird, List<PipePair> pipesCollection, List<Ground> groundList) {
        for (PipePair pipePair : pipesCollection) {
            if (collisionWithPipe(bird, pipePair.getDownerPipe()) || collisionWithPipe(bird, pipePair.getUpperPipe()))
                return true;
            if (!pipePair.isPassed() && bird.getLeftSide() > pipePair.getDownerPipe().getRightSide()){
                UserInformation.setGameScore(UserInformation.getGameScore() + 1);
                if (UserInformation.getGameScore() % 10 == 0){
                    upperGameLevel(groundList);
                }
                pipePair.setPassed(true);
            }
        }
        return false;
    }

    private boolean collisionWithPipe(Bird bird, OnePipe pipe) {
        return bird.getRightSide() > pipe.getLeftSide() && bird.getLeftSide() < pipe.getRightSide() && bird.getUpperSide() > pipe.getBottomSide() && bird.getBottomSide() < pipe.getUpperSide();
    }

    private void upperGameLevel(List<Ground> groundList) {
        for (Ground ground: groundList){
            ground.setSpeed(ground.getSpeed() * 1.2f);
        }
        UserInformation.setLevel(UserInformation.getLevel() + 1);
        UserInformation.setGamespeed(UserInformation.getGamespeed() * 1.2f);
        UserInformation.setCreatePipes((long) (UserInformation.getCreatePipes() / 1.2));
        UserInformation.setBirdSpeed(UserInformation.getBirdSpeed() * 1.05f);
    }
}
