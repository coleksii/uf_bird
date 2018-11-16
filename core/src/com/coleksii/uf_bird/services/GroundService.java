package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.Ground;

import java.util.List;

public interface GroundService {
    List<Ground> createGroundList();
    List<Ground> createBackGroundList();
    void processingSpeed(List<Ground> grounds);
}
