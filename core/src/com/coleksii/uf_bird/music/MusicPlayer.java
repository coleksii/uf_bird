package com.coleksii.uf_bird.music;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicPlayer{

    public static void music(){
        Music music = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));
        music.play();
        music.setLooping(true);
    }
}
