package com.starikcetin.ctis487.guessthenumber;

import android.content.Context;
import android.media.MediaPlayer;

public class Sound {
    public static void victory(Context ctx) {
        final MediaPlayer mp = MediaPlayer.create(ctx, R.raw.victory);
        mp.start();
    }

    public static void defeat(Context ctx) {
        final MediaPlayer mp = MediaPlayer.create(ctx, R.raw.defeat);
        mp.start();
    }
}
