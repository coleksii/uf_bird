package com.coleksii.uf_bird.model;

import com.badlogic.gdx.graphics.Texture;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ground extends Model {

    private boolean isColisianble;
    private float speed;

    public Ground(String value) {
        super(value);
    }
}
