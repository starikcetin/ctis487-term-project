package com.starikcetin.ctis487.guessthenumber.gameplay;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class GameSummary implements Serializable {
    public final List<Integer> targetNumber;
    public final int guessCount;
    public final int playtimeInSeconds;

    public GameSummary(List<Integer> targetNumber, int guessCount, int playtimeInSeconds) {
        this.targetNumber = targetNumber;
        this.guessCount = guessCount;
        this.playtimeInSeconds = playtimeInSeconds;
    }
}
