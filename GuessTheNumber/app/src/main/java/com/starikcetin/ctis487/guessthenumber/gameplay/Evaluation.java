package com.starikcetin.ctis487.guessthenumber.gameplay;

public class Evaluation {
    public final int correct;
    public final int misplaced;
    public final int wrong;

    public Evaluation(int correct, int misplaced, int wrong) {
        this.correct = correct;
        this.misplaced = misplaced;
        this.wrong = wrong;
    }
}
