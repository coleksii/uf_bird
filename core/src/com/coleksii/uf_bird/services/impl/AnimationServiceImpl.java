package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.services.AnimationService;

public class AnimationServiceImpl implements AnimationService {

    private float elapsedTime;

    @Override
    public Texture animatedBird(Bird bird) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        return bird.getAnimation().getKeyFrame(elapsedTime, true);
    }
}
