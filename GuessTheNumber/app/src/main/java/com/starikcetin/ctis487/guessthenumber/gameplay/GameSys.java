package com.starikcetin.ctis487.guessthenumber.gameplay;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventBus;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.CurrentGuessChangedEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GameOverEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.PlaytimeChangedEvent;

public class GameSys {
    public static final EventBus<CurrentGuessChangedEvent> currentGuessChangeEventBus = new EventBus<>();
    public static final EventBus<GuessEvent> guessEventBus = new EventBus<>();
    public static final EventBus<GameOverEvent> gameOverEventBus = new EventBus<>();
    public static final EventBus<PlaytimeChangedEvent> playtimeChangedEventBus = new EventBus<>();

    private static Game game;

    public static Game getGame() {
        return game;
    }

    /** starts a new game */
    public static void newGame(int digitCount) {
        game = new Game(digitCount);
    }

    /** tears-down the game */
    public static void cleanup() {
        game.close();
        game = null;
    }
}
