package com.starikcetin.ctis487.guessthenumber.gameplay;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventBus;

public class GameSys {
    public static final EventBus<CurrentGuessChangedEvent> currentGuessChangeEventBus = new EventBus<>();
    public static final EventBus<GuessEvent> guessEventBus = new EventBus<>();
    public static final EventBus<GameOverEvent> gameOverEventBus = new EventBus<>();

    private static Game game;

    public static Game getGame() {
        // TODO: delete, for debugging purposes
        if(game == null) {
            newGame(3);
        }

        return game;
    }

    /** starts a new game */
    public static void newGame(int digitCount) {
        game = new Game(digitCount);
        // TODO switch to game view
    }

    /** tears-down the game and goes to main menu */
    public static void goToMainMenu() {
        try {
            game.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        game = null;
        // TODO switch to main menu view
    }
}
