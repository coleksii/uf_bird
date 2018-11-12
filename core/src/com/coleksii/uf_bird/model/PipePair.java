package com.coleksii.uf_bird.model;

import com.coleksii.uf_bird.enums.TextureName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PipePair {
    private OnePipe upperPipe;
    private OnePipe downerPipe;
    private boolean isPassed = false;

    public PipePair() {
        this.upperPipe = new OnePipe(TextureName.PIPE_UPPER.getValue());
        this.downerPipe = new OnePipe(TextureName.PIPE_DOWN.getValue());
    }
}
