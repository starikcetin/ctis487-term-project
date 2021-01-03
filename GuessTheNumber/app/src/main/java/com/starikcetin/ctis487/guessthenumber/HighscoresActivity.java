package com.starikcetin.ctis487.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HighscoresActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    // ArrayList containing HashMap for RecyclerView
    private ArrayList<Highscore> highscoreList = new ArrayList();

    //Database related
    DBHelper dbHelper;

    IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hidding title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_highscores);

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dbHelper = new DBHelper(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.highscoreRecycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction("HIGHSCORE_JSON_PARSE_ACTION");
        registerReceiver(mIntentReceiver, mIntentFilter);

        Intent intent = new Intent(this, ParseHighscoreJSONService.class);
        intent.putExtra("filename", "highscores.json");
        startService(intent);

    }

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

    void fillListView() {
        ArrayList<Highscore> highscoresinHighscoreTable = HighscoreDB.getAllHighscores(dbHelper);
        HighscoreRecyclerViewAdapter adapter = new HighscoreRecyclerViewAdapter(HighscoresActivity.this, highscoresinHighscoreTable);
        mRecyclerView.setAdapter(adapter);
    }
}