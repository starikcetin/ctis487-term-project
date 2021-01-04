package com.starikcetin.ctis487.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.starikcetin.ctis487.guessthenumber.gameplay.GameSummary;

public class EndgameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);

        //hidding title bar
        getSupportActionBar().hide();

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();
        GameSummary gameSummary = (GameSummary) intent.getSerializableExtra("summary");
        boolean isVictory = intent.getBooleanExtra("isVictory", false);

        TextView numberTv = findViewById(R.id.endgame_number_textView);
        numberTv.setText(Utils.formatGuess(gameSummary.targetNumber));

        TextView playtimeTv = findViewById(R.id.endgame_time_textView);
        playtimeTv.setText(Utils.makeClockString(gameSummary.playtimeInSeconds));

        TextView guessCountTv = findViewById(R.id.endgame_guessCount_textView);
        guessCountTv.setText(String.valueOf(gameSummary.guessCount));

        if (isVictory) {
            Sound.victory(this);
        } else {
            Sound.defeat(this);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToMainMenu();
    }

    public void mainMenuOnClick(View view) {
        goToMainMenu();
    }

    private void goToMainMenu() {
        // launch main activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
