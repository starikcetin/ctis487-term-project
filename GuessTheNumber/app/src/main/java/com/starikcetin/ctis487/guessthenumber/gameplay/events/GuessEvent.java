package com.starikcetin.ctis487.guessthenumber.gameplay.events;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;
import com.starikcetin.ctis487.guessthenumber.gameplay.Evaluation;

public class GuessEvent extends EventWithArgs<GuessEvent.Args> {
    public GuessEvent(Object sender, GuessEvent.Args args) {
        super(sender, args);
    }

    public static class Args {
        public final Evaluation evaluation;
        public final int guessCount;

        public Args(Evaluation evaluation, int guessCount) {
            this.evaluation = evaluation;
            this.guessCount = guessCount;
        }
    }
}

