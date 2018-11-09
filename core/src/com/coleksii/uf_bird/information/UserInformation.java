package com.coleksii.uf_bird.information;

import com.coleksii.uf_bird.enums.States;

public class UserInformation {

    private States userState;
    private int score;
    private int latestScore;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLatestScore() {
        return latestScore;
    }

    public void setLatestScore(int latestScore) {
        this.latestScore = latestScore;
    }

    public UserInformation(States userState) {
        this.userState = userState;
    }

    public States getUserState() {
        return userState;
    }

    public void setUserState(States userState) {
        this.userState = userState;
    }
}
