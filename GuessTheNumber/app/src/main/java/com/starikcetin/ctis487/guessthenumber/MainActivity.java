package com.starikcetin.ctis487.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;

public class MainActivity extends AppCompatActivity {
    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hidding title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.playBtn) {
            GameSys.newGame(3);
            intent = new Intent(this, GameActivity.class);
        } else if (view.getId() == R.id.highscoreBtn) {
            intent = new Intent(this, HighscoresActivity.class);
        }
        startActivity(intent);
    }
}