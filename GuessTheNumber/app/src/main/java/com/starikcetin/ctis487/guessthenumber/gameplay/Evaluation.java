package com.starikcetin.ctis487.guessthenumber.gameplay;

import java.util.List;

public class Evaluation {
    public final List<Integer> guess;
    public final int correct;
    public final int misplaced;
    public final int wrong;

    public Evaluation(List<Integer> guess, int correct, int misplaced, int wrong) {
        this.guess = guess;
        this.correct = correct;
        this.misplaced = misplaced;
        this.wrong = wrong;
    }
}
