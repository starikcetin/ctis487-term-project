package com.starikcetin.ctis487.guessthenumber.gameplay.events;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventWithArgs;

public class PlaytimeChangedEvent extends EventWithArgs<PlaytimeChangedEvent.Args> {
    public PlaytimeChangedEvent(Object sender, Args args) {
        super(sender, args);
    }

    public static class Args {
        public final int playtimeInSeconds;

        public Args(int playtimeInSeconds) {
            this.playtimeInSeconds = playtimeInSeconds;
        }
    }
}
