package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.PipePair;

import java.util.List;

public interface PipesService {
    PipePair generatePiperPair();
    PipePair moveToStartPipePair(PipePair pipePair);
    List<PipePair> createStorePipes();
    void processingPipe(List<PipePair> pipesCollection, List<PipePair> storePipes);
}
