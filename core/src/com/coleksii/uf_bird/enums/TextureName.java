package com.coleksii.uf_bird.enums;

public enum TextureName {
    PIPE_DOWN("downPipe.png"),
    PIPE_UPPER("upperPipe.png"),
    BIRD("bird.png"),
    BACK("back.png"),
    XWING("xwing.png"),
    GROUND("ground.png"),
    GAMEOVER("gameover.png");

    private String value;

    public String getValue(){
        return value;
    }

    TextureName(String s) {
        value = s;
    }
}
