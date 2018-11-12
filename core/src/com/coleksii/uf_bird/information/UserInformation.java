package com.coleksii.uf_bird.information;

import com.coleksii.uf_bird.enums.State;


public class UserInformation {

    private static State userState = State.MAIN_MENU;
    private static int gamespeed = 3;
    private static int gameScore;
    private static int latestGameScore;


    public static int getGameScore() {
        return gameScore;
    }

    public static void setGameScore(int gameScore) {
        UserInformation.gameScore = gameScore;
    }

    public static int getLatestGameScore() {
        return latestGameScore;
    }

    public static void setLatestGameScore(int latestGameScore) {
        UserInformation.latestGameScore = latestGameScore;
    }


    public static int getGamespeed() {
        return gamespeed;
    }

    public static void setGamespeed(int gamespeed) {
        UserInformation.gamespeed = gamespeed;
    }


    public static State getUserState() {
        return userState;
    }

    public static void setUserState(State userState) {
        UserInformation.userState = userState;
    }
}
