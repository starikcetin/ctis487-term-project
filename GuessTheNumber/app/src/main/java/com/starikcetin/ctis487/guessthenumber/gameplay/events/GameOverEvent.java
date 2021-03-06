package com.starikcetin.ctis487.guessthenumber.gameplay.events;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;
import com.starikcetin.ctis487.guessthenumber.gameplay.GameSummary;

public class GameOverEvent extends EventWithArgs<GameOverEvent.Args> {
    public GameOverEvent(Object sender, Args args) {
        super(sender, args);
    }

    public static class Args {
        public final boolean isVictory;
        public final GameSummary gameSummary;

        public Args(boolean isVictory, GameSummary gameSummary) {
            this.isVictory = isVictory;
            this.gameSummary = gameSummary;
        }
    }
}

