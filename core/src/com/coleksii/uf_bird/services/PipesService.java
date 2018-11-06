package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.PipePair;

public interface PipesService {
    PipePair generatePiperPair();
    PipePair changeCurrentPiperPair(PipePair pipePair);
}
