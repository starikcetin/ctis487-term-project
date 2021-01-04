package com.starikcetin.ctis487.guessthenumber;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;

public class HighscoresActivity extends AppCompatActivity {
    private static final PrettyTime prettyTime = new PrettyTime();
    //Database related
    DBHelper dbHelper;
    IntentFilter mIntentFilter;
    private RecyclerView mRecyclerView;
    // ArrayList containing HashMap for RecyclerView
    private ArrayList<Highscore> highscoreList = new ArrayList();
    private BroadcastReceiver mIntentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Storing the received data into a Bundle
            Bundle b = intent.getExtras();
            highscoreList = b.getParcelableArrayList("highscorelist");
            for (Highscore hs : highscoreList) {
                HighscoreDB.insert(dbHelper, hs);
            }
            fillListView();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hidding title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_highscores);

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //database helper
        dbHelper = new DBHelper(this);

        //recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.highscoreRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //broadcase reciever intent filter
        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("HIGHSCORE_JSON_PARSE_ACTION");
        registerReceiver(mIntentReceiver, mIntentFilter);

        //json service
        Intent intent = new Intent(this, ParseHighscoreJSONService.class);
        intent.putExtra("filename", "highscores.json");
        startService(intent);

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // nothing
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        displayDialog(highscoreList.get(position));
                        Toast.makeText(HighscoresActivity.this, "Long Press Gesture!", Toast.LENGTH_SHORT).show();
                    }
                })
        );


    }

    void fillListView() {
        ArrayList<Highscore> highscoresinHighscoreTable = HighscoreDB.getAllHighscores(dbHelper);
        HighscoreRecyclerViewAdapter adapter = new HighscoreRecyclerViewAdapter(HighscoresActivity.this, highscoresinHighscoreTable);
        mRecyclerView.setAdapter(adapter);
    }

    public void displayDialog(Highscore selected) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_highscore);
        TextView dialogHeading = dialog.findViewById(R.id.tvDialogHeading);
        TextView dialogDetails = dialog.findViewById(R.id.tvDialogDetails);
        Button btnClose = dialog.findViewById(R.id.btnClose);

        dialogHeading.setText("HIGHSCORE");
        dialogDetails.setText("Score: " + selected.getScore() + "\n"
                + "Digits: " + selected.getDigitCount() + "\n"
                + "Guesses: " + selected.getGuessCount() + "\n"
                + "Playtime: " + selected.getPlayTime() + "\n"
                + "Date: " + prettyTime.format(selected.getTimestamp()) + "\n"
        );
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}