package com.starikcetin.ctis487.guessthenumber;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;

import java.util.ArrayList;
import java.util.Arrays;

public class GameGuessingFragment extends Fragment {
    private ArrayList<View> allNumpadButtons;

    public GameGuessingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_guessing, container, false);

        ImageButton deleteButton = view.findViewById(R.id.guessing_delete_button);
        deleteButton.setOnClickListener(this::deleteOnClick);

        ImageButton guessButton = view.findViewById(R.id.guessing_guess_button);
        guessButton.setOnClickListener(this::guessOnClick);

        Button num9Button = view.findViewById(R.id.guessing_9_button);
        num9Button.setOnClickListener(v -> this.numOnClick(v, 9));

        Button num8Button = view.findViewById(R.id.guessing_8_button);
        num8Button.setOnClickListener(v -> this.numOnClick(v, 8));

        Button num7Button = view.findViewById(R.id.guessing_7_button);
        num7Button.setOnClickListener(v -> this.numOnClick(v, 7));

        Button num6Button = view.findViewById(R.id.guessing_6_button);
        num6Button.setOnClickListener(v -> this.numOnClick(v, 6));

        Button num5Button = view.findViewById(R.id.guessing_5_button);
        num5Button.setOnClickListener(v -> this.numOnClick(v, 5));

        Button num4Button = view.findViewById(R.id.guessing_4_button);
        num4Button.setOnClickListener(v -> this.numOnClick(v, 4));

        Button num3Button = view.findViewById(R.id.guessing_3_button);
        num3Button.setOnClickListener(v -> this.numOnClick(v, 3));

        Button num2Button = view.findViewById(R.id.guessing_2_button);
        num2Button.setOnClickListener(v -> this.numOnClick(v, 2));

        Button num1Button = view.findViewById(R.id.guessing_1_button);
        num1Button.setOnClickListener(v -> this.numOnClick(v, 1));

        allNumpadButtons = new ArrayList<>(Arrays.asList(num1Button, num2Button, num3Button, num4Button, num5Button, num6Button, num7Button, num8Button, num9Button));

        return view;
    }

    private void numOnClick(View view, int number) {
        boolean isValid = GameSys.getGame().digitInput(number);
        if(isValid) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    private void guessOnClick(View view) {
        boolean isValid = GameSys.getGame().guessInput();
        if(isValid) {
            allNumpadButtons.forEach(b -> b.setVisibility(View.VISIBLE));
        }
    }

    private void deleteOnClick(View v) {
        int deletedDigit = GameSys.getGame().deleteInput();
        if(deletedDigit != -1) {
            allNumpadButtons.get(deletedDigit - 1).setVisibility(View.VISIBLE);
        }
    }
}
