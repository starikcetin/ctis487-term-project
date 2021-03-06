package com.starikcetin.ctis487.guessthenumber.eventbus;

import java.util.ArrayList;

public class EventBus<TEvent extends Event> {
    private final ArrayList<EventListener<TEvent>> _listeners = new ArrayList<>();

    public void emit(TEvent event) {
        _listeners.forEach(l -> l.OnEvent(event));
    }

    public void addListener(EventListener<TEvent> listener) {
        _listeners.add(listener);
    }

    public void removeListener(EventListener<TEvent> listener) {
        _listeners.remove(listener);
    }
}
