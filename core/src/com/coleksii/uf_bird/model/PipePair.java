package com.coleksii.uf_bird.model;

import com.coleksii.uf_bird.enums.TextureName;

public class PipePair {
    private OnePipe upperPipe;
    private OnePipe downerPipe;

    public PipePair() {
        this.upperPipe = new OnePipe(TextureName.PIPE_UPPER.getValue());
        this.downerPipe = new OnePipe(TextureName.PIPE_DOWN.getValue());
    }

    public OnePipe getUpperPipe() {
        return upperPipe;
    }

    public void setUpperPipe(OnePipe upperPipe) {
        this.upperPipe = upperPipe;
    }

    public OnePipe getDownerPipe() {
        return downerPipe;
    }

    public void setDownerPipe(OnePipe downerPipe) {
        this.downerPipe = downerPipe;
    }
}
