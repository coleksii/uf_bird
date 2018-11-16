package com.coleksii.uf_bird;

import com.badlogic.gdx.ApplicationAdapter;
import com.coleksii.uf_bird.background.GameProcessor;
import com.coleksii.uf_bird.music.MusicPlayer;

public class MyGdxGame extends ApplicationAdapter {

    private GameProcessor gameProcessor;

    @Override
    public void create() {
        gameProcessor = new GameProcessor();
        MusicPlayer.music();
    }

    @Override
    public void render() {
        gameProcessor.processing();
    }

    @Override
    public void dispose() {
    }
}
