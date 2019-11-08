package com.kys.openapi.app.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecuteTimer {

    private long startTime;

    private long endTime;

    public static ExecuteTimer newTimer(){
        return new ExecuteTimer();
    }

    public void start(){
        this.startTime = System.currentTimeMillis();
    }

    public void end(){
        this.endTime = System.currentTimeMillis();
    }

    public long calc(){
        return endTime - startTime;
    }
}
