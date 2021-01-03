package com.starikcetin.ctis487.guessthenumber.gameplay;

public class GameSys {
    private static Game game;

    public static Game getGame() {
        return game;
    }

    public static void newGame(int digitCount) {
        game = new Game(digitCount);
    }
}
