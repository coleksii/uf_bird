package com.coleksii.uf_bird.services.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coleksii.uf_bird.enums.State;
import com.coleksii.uf_bird.information.UserInformation;
import com.coleksii.uf_bird.services.ButtonService;

public class ButtonServiceImpl implements ButtonService {
    private Skin skin;
    private BitmapFont font;


    public ButtonServiceImpl() {
        font = new BitmapFont();
        skin = createBasicSkin();
    }

    @Override
    public TextButton createNewGameButton() {
        TextButton newGameButton = new TextButton("Start game", skin);
        newGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/2 + (newGameButton.getHeight()));
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                UserInformation.setUserState(State.PREPARE);
            }
        });
        return newGameButton;
    }

    @Override
    public TextButton createExitButton() {
        TextButton exitGameButton = new TextButton("Exit", skin);
        exitGameButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/4 + (exitGameButton.getHeight()));
        exitGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();

            }
        });
        return exitGameButton;
    }

    @Override
    public Actor createToMainMenuButton() {
        TextButton toMainMenuButton = new TextButton("To Main Menu", skin);
        toMainMenuButton.setPosition(Gdx.graphics.getWidth()/2 - Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()/2);
        toMainMenuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                UserInformation.setUserState(State.MAIN_MENU);

            }
        });
        return toMainMenuButton;
    }

    private Skin createBasicSkin(){
        Skin skin = new Skin();
        skin.add("default", font);
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        return skin;
    }

}
