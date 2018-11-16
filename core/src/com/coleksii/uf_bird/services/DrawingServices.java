package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.Ground;
import com.coleksii.uf_bird.model.PipePair;

import java.util.List;

public interface DrawingServices {
    void drawing(Bird bird, List<PipePair> pipesCollection, List<Ground> groundList, List<Ground> backGroundList);
}
