package com.starikcetin.ctis487.guessthenumber;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventListener;
import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.GuessEventListener;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.PlaytimeChangedEvent;
import com.starikcetin.ctis487.guessthenumber.gameplay.events.PlaytimeChangedEventListener;

import java.util.Locale;

public class GameActivity extends FragmentActivity {

    private TextView clockTextView;
    private TextView guessCountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        clockTextView = findViewById(R.id.game_clock_textView);
        clockTextView.setText("00:00");

        guessCountTextView = findViewById(R.id.game_guessCount_textView);
        guessCountTextView.setText("0");

        ViewPager2 pager = (ViewPager2) findViewById(R.id.game_body_viewPager_static);
        pager.setAdapter(new GamePagerAdapter(this));
        pager.setCurrentItem(GamePagerAdapter.GUESSING_PAGE_INDEX, false);

        GameSys.playtimeChangedEventBus.addListener(this::OnEvent);
        GameSys.guessEventBus.addListener(this::OnEvent);
    }

    @Override
    protected void onDestroy() {
        GameSys.playtimeChangedEventBus.removeListener(this::OnEvent);
        GameSys.guessEventBus.removeListener(this::OnEvent);

        super.onDestroy();
    }

    public void OnEvent(PlaytimeChangedEvent event) {
        String clockStr = makeClockString(event.args.playtimeInSeconds);
        clockTextView.setText(clockStr);
    }

    public void OnEvent(GuessEvent event) {
        String guessCountStr = String.format(Locale.getDefault(), "%d", event.args.guessCount);
        guessCountTextView.setText(guessCountStr);
    }

    private String makeClockString(int playtimeInSeconds) {
        int minutes = playtimeInSeconds / 60;
        int seconds = playtimeInSeconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
}
