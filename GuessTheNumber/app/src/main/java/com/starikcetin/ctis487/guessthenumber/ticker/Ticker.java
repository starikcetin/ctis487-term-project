package com.starikcetin.ctis487.guessthenumber.ticker;

import android.os.Handler;

public class Ticker {
    private final Handler handler;
    private final Runnable runnable;

    public Ticker(int periodInMs, Action onTick) {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                onTick.Action();
                handler.postDelayed(this, periodInMs);
            }
        };

        handler.postDelayed(runnable, periodInMs);
    }

    public void stop() {
        handler.removeCallbacks(runnable);
    }
}

