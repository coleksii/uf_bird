package com.coleksii.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.coleksii.uf_bird.background.GraphicObjProcessor;

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
