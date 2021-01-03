package com.starikcetin.ctis487.guessthenumber;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.starikcetin.ctis487.guessthenumber.eventbus.EventListener;
import com.starikcetin.ctis487.guessthenumber.gameplay.GameSys;
import com.starikcetin.ctis487.guessthenumber.gameplay.PlaytimeChangedEvent;

import java.util.Locale;

public class GameActivity extends FragmentActivity implements EventListener<PlaytimeChangedEvent> {

    private TextView clockTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        clockTextView = findViewById(R.id.game_clock_textView);

        ViewPager2 pager = (ViewPager2) findViewById(R.id.game_body_viewPager_static);
        pager.setAdapter(new GamePagerAdapter(this));
        pager.setCurrentItem(GamePagerAdapter.GUESSING_PAGE_INDEX, false);

        GameSys.playtimeChangedEventBus.addListener(this);
    }

    @Override
    protected void onDestroy() {
        GameSys.playtimeChangedEventBus.removeListener(this);

        super.onDestroy();
    }

    @Override
    public void OnEvent(PlaytimeChangedEvent event) {
        String clockStr = makeClockString(event.args.playtimeInSeconds);
        clockTextView.setText(clockStr);
    }

    private String makeClockString(int playtimeInSeconds) {
        int minutes = playtimeInSeconds / 60;
        int seconds = playtimeInSeconds % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }
}
