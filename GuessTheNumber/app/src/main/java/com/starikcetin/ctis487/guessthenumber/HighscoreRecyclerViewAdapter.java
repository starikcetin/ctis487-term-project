package com.starikcetin.ctis487.guessthenumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;

public class HighscoreRecyclerViewAdapter extends RecyclerView.Adapter<HighscoreRecyclerViewAdapter.MyRecyclerViewItemHolder> {
    private static final PrettyTime prettyTime = new PrettyTime();

    private Context context;
    private ArrayList<Highscore> mArrayList;

    public HighscoreRecyclerViewAdapter(Context context, ArrayList<Highscore> values) {
        this.context = context;
        this.mArrayList = values;
    }

    @NonNull
    @Override
    public MyRecyclerViewItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflator = LayoutInflater.from(viewGroup.getContext());

        View itemView = inflator.inflate(R.layout.recycler_item, viewGroup, false);

        MyRecyclerViewItemHolder mViewHolder = new MyRecyclerViewItemHolder(itemView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewItemHolder myRecyclerViewItemHolder, int i) {

        final Highscore hs = mArrayList.get(i);

        myRecyclerViewItemHolder.digitsTv.setText(hs.getDigitCount() + "");
        myRecyclerViewItemHolder.scoreTv.setText(hs.getScore() + "");
        myRecyclerViewItemHolder.guessesTv.setText(hs.getGuessCount() + "");
        myRecyclerViewItemHolder.playtimeTv.setText(hs.getPlayTime() + "");
        myRecyclerViewItemHolder.timestampTv.setText(prettyTime.format(hs.getTimestamp()) + "");


        myRecyclerViewItemHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HighscoresActivity) context).displayDialog(hs);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class MyRecyclerViewItemHolder extends RecyclerView.ViewHolder {
        TextView digitsTv, guessesTv, scoreTv, playtimeTv, timestampTv;
        ConstraintLayout parentLayout;

        public MyRecyclerViewItemHolder(@NonNull View itemView) {
            super(itemView);
            digitsTv = itemView.findViewById(R.id.digitsTv);
            guessesTv = itemView.findViewById(R.id.guessesTv);
            scoreTv = itemView.findViewById(R.id.scoreTv);
            playtimeTv = itemView.findViewById(R.id.playtimeTv);
            timestampTv = itemView.findViewById(R.id.timestampTv);
            parentLayout = itemView.findViewById(R.id.itemConstraintLayout);
        }
    }

}
