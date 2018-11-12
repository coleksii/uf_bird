package com.coleksii.uf_bird.services;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.coleksii.uf_bird.information.UserInformation;

public interface ButtonService {
    TextButton createNewGameButton();
    TextButton createExitButton();
    Actor createToMainMenuButton();
}
