package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.model.Ground;

import java.util.List;

public interface BackGroundService {
    List<Ground> createGroundList();
    void processingSpeed(List<Ground> grounds);
}
