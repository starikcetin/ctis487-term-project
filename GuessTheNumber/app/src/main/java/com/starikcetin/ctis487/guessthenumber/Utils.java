package com.starikcetin.ctis487.guessthenumber;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static String formatGuess(List<Integer> guess) {
        return guess.stream().map(String::valueOf).collect(Collectors.joining());
    }
}
