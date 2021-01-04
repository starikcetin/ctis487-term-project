package com.starikcetin.ctis487.guessthenumber;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class GamePagerAdapter extends FragmentStateAdapter {
    public static final int PAGE_COUNT = 3;
    public static final int OPTIONS_PAGE_INDEX = 0;
    public static final int GUESSING_PAGE_INDEX = 1;
    public static final int PREV_GUESSES_PAGE_INDEX = 2;

    public GamePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case OPTIONS_PAGE_INDEX:
                return new GameOptionsFragment();
            case GUESSING_PAGE_INDEX:
                return new GameGuessingFragment();
            case PREV_GUESSES_PAGE_INDEX:
                return new GamePrevGuessesFragment();
        }

        throw new IndexOutOfBoundsException("GamePager: position " + position + " is invalid.");
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }
}
