package com.starikcetin.ctis487.guessthenumber.eventbus;

public class EventWithArgs<TArgs> extends Event {
    public final TArgs args;

    public EventWithArgs(Object sender, TArgs args) {
        super(sender);
        this.args = args;
    }
}
