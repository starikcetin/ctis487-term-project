package com.starikcetin.ctis487.guessthenumber;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;

public class GameOptionsFragment extends Fragment {
    public GameOptionsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_options, container, false);

        Button mainMenuButton = view.findViewById(R.id.options_mainMenu_button);
        mainMenuButton.setOnClickListener(this::mainMenuOnClick);

        Button revealButton = view.findViewById(R.id.options_reveal_button);
        revealButton.setOnClickListener(this::revealOnClick);

        return view;
    }

    private void revealOnClick(View view) {
        GameSys.getGame().reveal();
    }

    private void mainMenuOnClick(View view) {
        GameSys.cleanup();
        // TODO: switch to main menu
    }
}
