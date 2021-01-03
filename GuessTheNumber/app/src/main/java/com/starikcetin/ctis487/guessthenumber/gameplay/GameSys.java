package com.starikcetin.ctis487.guessthenumber.gameplay;

public class GameSys {
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
        game = null;
        // TODO switch to main menu view
    }
}
