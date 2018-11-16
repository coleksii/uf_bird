package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.PipePair;

import java.util.List;

public interface CollisionAndScoreService {
    boolean collisionAndScoreProcess(Bird bird, List<PipePair> pipesCollection, List<Ground> groundList);
}
