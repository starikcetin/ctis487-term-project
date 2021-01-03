package com.starikcetin.ctis487.guessthenumber.gameplay;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;

import java.util.ArrayList;
import java.util.List;

public class CurrentGuessChangedEvent extends EventWithArgs<CurrentGuessChangedEvent.Args> {
    public CurrentGuessChangedEvent(Object sender, Args args) {
        super(sender, args);
    }

    public static class Args {
        public final List<Integer> currentGuess;

        public Args(List<Integer> currentGuess) {
            this.currentGuess = currentGuess;
        }
    }
}
