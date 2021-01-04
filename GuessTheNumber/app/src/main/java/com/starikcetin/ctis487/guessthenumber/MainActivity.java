package com.starikcetin.ctis487.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;

public class MainActivity extends AppCompatActivity {
    Intent intent = null;
    private Group digitSelectGroup;
    private Button playBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hidding title bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        digitSelectGroup = findViewById(R.id.digitSelectGroup);
        playBtn = findViewById(R.id.playBtn);
    }

    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.playBtn) {
            playBtn.setVisibility(View.GONE);
            digitSelectGroup.setVisibility(View.VISIBLE);
        } else if (id == R.id.highscoreBtn) {
            intent = new Intent(this, HighscoresActivity.class);
            startActivity(intent);
        } else if (id == R.id.threeDigitsBtn) {
            launchGame(3);
        } else if (id == R.id.fiveDigitsBtn) {
            launchGame(5);
        } else if (id == R.id.sevenDigitsBtn) {
            launchGame(7);
        }
    }

    private void launchGame(int digitCount) {
        GameSys.newGame(digitCount);
        intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
