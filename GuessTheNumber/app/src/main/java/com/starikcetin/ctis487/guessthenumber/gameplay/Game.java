package com.starikcetin.ctis487.guessthenumber.gameplay;

public class Game {
    public final int digitCount;

    public Game(int digitCount) {
        this.digitCount = digitCount;
    }

    /** returns whether the input is valid or not */
    public boolean digitInput(int digit) {
        // TODO implement
        return true;
    }

    /** returns whether the guess is valid or not (validity is NOT whether the guess is correct) */
    public boolean guessInput() {
        // TODO implement
        return true;
    }

    /** returns the deleted digit, or -1 if invalid. */
    public int deleteInput() {
        // TODO implement
        return 1;
    }

    /** reveals the answer, triggers unsuccessful game over */
    public void reveal() {
        // TODO implement
    }
}
