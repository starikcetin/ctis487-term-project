package com.starikcetin.ctis487.guessthenumber.eventbus;

public interface EventListener<TEvent extends Event> {
    void OnEvent(TEvent event);
}
