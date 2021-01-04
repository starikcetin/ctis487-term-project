package com.starikcetin.ctis487.guessthenumber;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Utils {
    public static String formatGuess(List<Integer> guess) {
        return guess.stream().map(String::valueOf).collect(Collectors.joining());
    }

    public static String makeClockString(int playtimeInSeconds) {
        int minutes = playtimeInSeconds / 60;
        int seconds = playtimeInSeconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
}
