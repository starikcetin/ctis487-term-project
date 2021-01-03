package com.starikcetin.ctis487.guessthenumber.gameplay;

import java.util.List;

public class GameSummary {
    public final List<Integer> targetNumber;
    public final int guessCount;
    public final int playtimeInSeconds;

    public GameSummary(List<Integer> targetNumber, int guessCount, int playtimeInSeconds) {
        this.targetNumber = targetNumber;
        this.guessCount = guessCount;
        this.playtimeInSeconds = playtimeInSeconds;
    }
}
