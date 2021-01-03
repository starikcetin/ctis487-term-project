package com.starikcetin.ctis487.guessthenumber.gameplay;

import android.util.Log;

import com.starikcetin.ctis487.guessthenumber.ticker.Ticker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements AutoCloseable {
    public final int digitCount;
    private final List<Integer> targetNumber;
    private final List<Integer> currentGuess = new ArrayList<>();
    private final Ticker playtimeTicker;
    private int playtimeInSeconds = 0;
    private int guessCount = 0;

    public Game(int digitCount) {
        this.digitCount = digitCount;

        List<Integer> possibleDigits = Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1);
        ArrayList<Integer> targetNumber = new ArrayList<>();

        for (int i = 0; i < digitCount; i++) {
            int rndIndex = ThreadLocalRandom.current().nextInt(0, possibleDigits.size());
            int rndDigit = possibleDigits.remove(rndIndex);
            targetNumber.add(rndDigit);
        }

        this.targetNumber = Collections.unmodifiableList(targetNumber);

        playtimeTicker = new Ticker(1000, () -> playtimeInSeconds++);
    }

    /** returns whether the input is valid or not */
    public boolean digitInput(int digit) {
        int currentGuessSize = currentGuess.size();
        boolean isCurrentGuessFull = currentGuessSize >= digitCount - 1;

        if(!isCurrentGuessFull) {
            return false;
        }

        currentGuess.add(digit);
        onCurrentGuessChanged();
        return true;
    }

    /** returns whether the guess is valid or not (validity is NOT whether the guess is correct) */
    public boolean guessInput() {
        int currentGuessSize = currentGuess.size();
        boolean isCurrentGuessFull = currentGuessSize >= digitCount - 1;

        if(!isCurrentGuessFull) {
            return false;
        }

        Evaluation evaluation = evaluate();
        onGuess(evaluation);

        if(evaluation.correct == digitCount) {
            onGameOver(true);
        }

        return true;
    }

    private void onGuess(Evaluation evaluation) {
        guessCount++;

        GuessEvent.Args args = new GuessEvent.Args(evaluation);
        GuessEvent event = new GuessEvent(this, args);
        GameSys.guessEventBus.emit(event);
    }

    /** returns the deleted digit, or -1 if invalid. */
    public int deleteInput() {
        int currentGuessSize = currentGuess.size();

        if(currentGuessSize <= 0) {
            return -1;
        }

        int removed = currentGuess.remove(currentGuessSize);
        onCurrentGuessChanged();
        return removed;
    }

    /** reveals the answer, triggers unsuccessful game over */
    public void reveal() {
        onGameOver(false);
    }

    private Evaluation evaluate() {
        int correct = 0;
        int misplaced = 0;
        int wrong = 0;

        for (int i = 0; i < currentGuess.size(); i++) {
            int guessDigit = currentGuess.get(i);
            int targetDigit = targetNumber.get(i);

            // 1. check for correct
            if(guessDigit == targetDigit) {
                correct++;
                continue;
            }

            // 2. check for misplaced
            if(targetNumber.contains(guessDigit)) {
                misplaced++;
                continue;
            }

            // 3. if we are here, it is wrong
            wrong++;
        }

        return new Evaluation(correct, misplaced, wrong);
    }

    private void onCurrentGuessChanged() {
        CurrentGuessChangedEvent.Args args = new CurrentGuessChangedEvent.Args(currentGuess);
        CurrentGuessChangedEvent event = new CurrentGuessChangedEvent(this, args);
        GameSys.currentGuessChangeEventBus.emit(event);
    }

    private void onGameOver(boolean isVictory) {
        playtimeTicker.stop();

        GameSummary gameSummary = new GameSummary(targetNumber, guessCount, playtimeInSeconds);
        GameOverEvent.Args args = new GameOverEvent.Args(isVictory, gameSummary);
        GameOverEvent event = new GameOverEvent(this, args);
        GameSys.gameOverEventBus.emit(event);
    }

    // ---------- AutoClose ----------
    private boolean alreadyClosed = false;

    @Override
    public void close() throws Exception {
        if(alreadyClosed) {
            Log.w("AlreadyClosed", "This Game class was already closed! Ignoring.");
            return;
        }

        alreadyClosed = true;

        // cleanup
        playtimeTicker.stop();
    }
}
