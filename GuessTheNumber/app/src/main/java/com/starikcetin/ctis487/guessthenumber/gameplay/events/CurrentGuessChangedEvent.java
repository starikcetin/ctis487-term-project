package com.starikcetin.ctis487.guessthenumber.gameplay.events;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;

import java.util.List;

public class CurrentGuessChangedEvent extends EventWithArgs<CurrentGuessChangedEvent.Args> {
    public CurrentGuessChangedEvent(Object sender, Args args) {
        super(sender, args);
    }

    public static class Args {
        public final List<Integer> currentGuess;
        public final int digitCount;

        public Args(List<Integer> currentGuess, int digitCount) {
            this.currentGuess = currentGuess;
            this.digitCount = digitCount;
        }
    }
}

