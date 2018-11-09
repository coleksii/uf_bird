package com.coleksii.uf_bird.services;

import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.PipePair;

import java.util.List;

public interface DrawingServices {
    void drawing(UserInformation userInformation, Bird bird, List<PipePair> pipesCollection);
}
