package com.coleksii.uf_bird.services.impl;

import com.coleksii.uf_bird.services.TimeService;

public class TimeServiceImpl implements TimeService{

    private static final long ONE_SECOND = 1000000000;
    private static final long CREATE_PIPES = (long) (ONE_SECOND * 2);
    private static final long MAIN = ONE_SECOND / 1000;
    private long start;
    private long mainCycle;
    private long createPipeCycle;
    

    public TimeServiceImpl(){
        start = System.nanoTime();
        mainCycle = start;
        createPipeCycle = start;
    }

    @Override
    public boolean isTimeOutMainCycle() {
        if (System.nanoTime() - mainCycle > MAIN){
            mainCycle = System.nanoTime();
            return true;
        }
        return false;
    }

    @Override
    public boolean isTimeTocreatePipe() {
        if (System.nanoTime() - createPipeCycle > CREATE_PIPES){
            createPipeCycle = System.nanoTime();
            return true;
        }
        return false;
    }
}
