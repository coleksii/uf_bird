package com.coleksii.uf_bird.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ground extends Model {

    private boolean isColisianble;
    private int speed;

    public Ground(String value) {
        super(value);
    }
}
