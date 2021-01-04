package com.starikcetin.ctis487.guessthenumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventListener;
import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.CurrentGuessChangedEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GameOverEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEventListener;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.PlaytimeChangedEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.PlaytimeChangedEventListener;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    private TextView clockTextView;
    private TextView guessCountTextView;
    private TextView currentGuessTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //hidding title bar
        getSupportActionBar().hide();

        //hidding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        clockTextView = findViewById(R.id.game_clock_textView);
        clockTextView.setText("00:00");

        guessCountTextView = findViewById(R.id.game_guessCount_textView);
        guessCountTextView.setText("0");

        currentGuessTextView = findViewById(R.id.game_currentGuess_textView);
        currentGuessTextView.setText(new String(new char[GameSys.getGame().digitCount]).replace("\0", "_"));

        ViewPager2 pager = (ViewPager2) findViewById(R.id.game_body_viewPager_static);
        pager.setAdapter(new GamePagerAdapter(this));
        pager.setCurrentItem(GamePagerAdapter.GUESSING_PAGE_INDEX, false);

        // Default offscreen page limit is 1. We start at 0, and then switch to 1 programmatically.
        // This causes the fragment at index 2 (prev guess list) to not get initialized until switched.
        // Setting the offscreen page limit to 2 forces the viewpager to initialize both indexes
        // 1 and 2 at the beginning.
        pager.setOffscreenPageLimit(2);

        GameSys.playtimeChangedEventBus.addListener(this::OnEvent);
        GameSys.guessEventBus.addListener(this::OnEvent);
        GameSys.gameOverEventBus.addListener(this::OnEvent);
        GameSys.currentGuessChangeEventBus.addListener(this::OnEvent);
    }

    @Override
    protected void onDestroy() {
        GameSys.playtimeChangedEventBus.removeListener(this::OnEvent);
        GameSys.guessEventBus.removeListener(this::OnEvent);
        GameSys.cleanup();

        super.onDestroy();
    }

    public void OnEvent(PlaytimeChangedEvent event) {
        String clockStr = Utils.makeClockString(event.args.playtimeInSeconds);
        clockTextView.setText(clockStr);
    }

    public void OnEvent(GuessEvent event) {
        String guessCountStr = String.format(Locale.getDefault(), "%d", event.args.guessCount);
        guessCountTextView.setText(guessCountStr);
    }

    public void OnEvent(GameOverEvent event) {
        // cleanup, since game is over
        GameSys.cleanup();

        // launch endgame activity
        Intent intent = new Intent(this, EndgameActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isVictory", event.args.isVictory);
        bundle.putSerializable("summary", event.args.gameSummary);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void OnEvent(CurrentGuessChangedEvent event) {
        String partial = Utils.formatGuess(event.args.currentGuess);
        String format = "%-" + event.args.digitCount + "s";
        String result = String.format(Locale.getDefault(), format, partial).replace(' ', '_');
        currentGuessTextView.setText(result);
    }
}
