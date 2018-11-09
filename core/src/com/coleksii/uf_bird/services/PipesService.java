package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.PipePair;

import java.util.List;

public interface PipesService {
    PipePair generatePiperPair();
    PipePair changeCurrentPiperPair(PipePair pipePair);
    List<PipePair> createStorePipes();
}
