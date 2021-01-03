package com.starikcetin.ctis487.guessthenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.starikcetin.ctis487.guessthenumber.gameplay.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GuessListRecyclerViewAdapter extends RecyclerView.Adapter<GuessListRecyclerViewAdapter.ViewHolder> {
    private final List<Evaluation> guesses = new ArrayList<>();

    public void addGuess(Evaluation guess) {
        guesses.add(guess);
        notifyItemInserted(guesses.size() - 1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_recycler_view_guess_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.fill(position, guesses.get(position));
    }

    @Override
    public int getItemCount() {
        return guesses.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView indexTv;
        private final TextView guessTv;
        private final TextView correctTv;
        private final TextView misplacedTv;
        private final TextView wrongTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            indexTv = itemView.findViewById(R.id.item_guessList_index_textView);
            guessTv = itemView.findViewById(R.id.item_guessList_guess_textView);
            correctTv = itemView.findViewById(R.id.item_guessList_correct_textView);
            misplacedTv = itemView.findViewById(R.id.item_guessList_misplaced_textView);
            wrongTv = itemView.findViewById(R.id.item_guessList_wrong_textView);
        }

        public void fill(int index, Evaluation evaluation) {
            indexTv.setText(String.format(Locale.getDefault(), "%d", index));
            String guess = Utils.formatGuess(evaluation.guess);
            guessTv.setText(guess);
            correctTv.setText(String.format(Locale.getDefault(), "%d", evaluation.correct));
            misplacedTv.setText(String.format(Locale.getDefault(), "%d", evaluation.misplaced));
            wrongTv.setText(String.format(Locale.getDefault(), "%d", evaluation.wrong));
        }
    }
}
