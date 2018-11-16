package com.coleksii.uf_bird.information;

import com.coleksii.uf_bird.enums.State;


public class UserInformation {

    private static final long ONE_SECOND = 1000000000;
    private static long CREATE_PIPES = (long) (ONE_SECOND * 2);
    private static State userState = State.MAIN_MENU;
    private static float gamespeed = 3;
    private static int gameScore;
    private static int latestGameScore;
    private static float birdSpeed = 5;
    private static int level = 1;

    public static long getOneSecond() {
        return ONE_SECOND;
    }

    public static long getCreatePipes() {
        return CREATE_PIPES;
    }

    public static void setCreatePipes(long createPipes) {
        CREATE_PIPES = createPipes;
    }

    public static float getBirdSpeed() {
        return birdSpeed;
    }

    public static void setBirdSpeed(float birdSpeed) {
        UserInformation.birdSpeed = birdSpeed;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        UserInformation.level = level;
    }

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


    public static float getGamespeed() {
        return gamespeed;
    }

    public static void setGamespeed(float gamespeed) {
        UserInformation.gamespeed = gamespeed;
    }


    public static State getUserState() {
        return userState;
    }

    public static void setUserState(State userState) {
        UserInformation.userState = userState;
    }

    public static void toDefault(){
        userState = State.MAIN_MENU;
        gamespeed = 3;
        level = 1;
        birdSpeed = 5;
        latestGameScore = gameScore;
        CREATE_PIPES = ONE_SECOND * 2;
        gameScore = 0;
    }
}
