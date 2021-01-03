package com.starikcetin.ctis487.guessthenumber;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEvent;

public class GamePrevGuessesFragment extends Fragment {
    private GuessListRecyclerViewAdapter guessListAdapter;

    public GamePrevGuessesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_prev_guesses, container, false);
        Context ctx = getContext();

        guessListAdapter = new GuessListRecyclerViewAdapter();

        RecyclerView guessListRecyclerView = view.findViewById(R.id.prevGuesses_guessList_recyclerView);
        guessListRecyclerView.setAdapter(guessListAdapter);

        LinearLayoutManager guessListLayoutManager = new LinearLayoutManager(ctx);
        guessListLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        guessListRecyclerView.setLayoutManager(guessListLayoutManager);

        GameSys.guessEventBus.addListener(this::onGuess);

        return view;
    }

    @Override
    public void onDestroyView() {
        GameSys.guessEventBus.removeListener(this::onGuess);
        super.onDestroyView();
    }

    private void onGuess(GuessEvent guessEvent) {
        guessListAdapter.addGuess(guessEvent.args.evaluation);
    }
}
