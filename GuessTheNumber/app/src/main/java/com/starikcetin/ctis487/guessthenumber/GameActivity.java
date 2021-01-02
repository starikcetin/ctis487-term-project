package com.starikcetin.ctis487.guessthenumber;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

public class GameActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ViewPager2 pager = (ViewPager2) findViewById(R.id.game_body_viewPager_static);
        pager.setAdapter(new GamePagerAdapter(this));
        pager.setCurrentItem(GamePagerAdapter.GUESSING_PAGE_INDEX, false);
    }
}
