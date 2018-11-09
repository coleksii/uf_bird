package com.coleksii.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.coleksii.uf_bird.background.GraphicObjProcessor;
import com.coleksii.uf_bird.enums.States;
import com.coleksii.uf_bird.enums.TextureName;
import com.coleksii.uf_bird.model.Bird;
import com.coleksii.uf_bird.model.OnePipe;
import com.coleksii.uf_bird.model.PipePair;
import com.coleksii.uf_bird.services.ButtonService;
import com.coleksii.uf_bird.services.PipesService;
import com.coleksii.uf_bird.services.TimeService;
import com.coleksii.uf_bird.services.impl.ButtonServiceImpl;
import com.coleksii.uf_bird.services.impl.PiperServiceImpl;
import com.coleksii.uf_bird.services.impl.TimeServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {

    private GraphicObjProcessor graphicObjProcessor;


    @Override
    public void create() {
        graphicObjProcessor = new GraphicObjProcessor();
    }

    @Override
    public void render() {
        graphicObjProcessor.processing();
    }

    @Override
    public void dispose() {
    }
}
