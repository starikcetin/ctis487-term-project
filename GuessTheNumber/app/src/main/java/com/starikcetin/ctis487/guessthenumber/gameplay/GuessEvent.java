package com.starikcetin.ctis487.guessthenumber.gameplay;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;

public class GuessEvent extends EventWithArgs<GuessEvent.Args> {
    public GuessEvent(Object sender, GuessEvent.Args args) {
        super(sender, args);
    }

    public static class Args {
        public final Evaluation evaluation;

        public Args(Evaluation evaluation) {
            this.evaluation = evaluation;
        }
    }
}

