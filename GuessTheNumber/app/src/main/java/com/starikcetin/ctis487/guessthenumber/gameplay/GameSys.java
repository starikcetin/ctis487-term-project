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

    public static void newGame(int digitCount) {
        game = new Game(digitCount);
    }
}
