package com.starikcetin.ctis487.guessthenumber;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

public class ParseHighscoreJSONService extends IntentService {
    JSONArray highscores;
    ArrayList<Highscore> highscoreList;
    public static final String TAG_HIGHSCORES = "highscores";
    public static final String TAG_HIGHSCORE = "highscore";
    public static final String TAG_SCORE = "score";
    public static final String TAG_DIGITS = "digits";
    public static final String TAG_GUESSES = "guesses";
    public static final String TAG_PLAYTIME = "playtime";
    public static final String TAG_TIMESTAMP = "timestamp";

    public ParseHighscoreJSONService() {
        super("MyServiceHighscore");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        highscoreList = new ArrayList<Highscore>();

        String filename = intent.getStringExtra("filename");

        String jsonfileContent = loadFileFromAsset(filename);

        Log.d("Response: ", "> " + jsonfileContent);

        if (jsonfileContent != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonfileContent);

                // Getting JSON Array node
                highscores = jsonObj.getJSONArray(TAG_HIGHSCORES);

                // looping through all Contacts
                for (int i = 0; i < highscores.length(); i++) {
                    JSONObject highscore = highscores.getJSONObject(i);

                    int score = highscore.getInt(TAG_SCORE);
                    int digits = highscore.getInt(TAG_DIGITS);
                    int guesses = highscore.getInt(TAG_GUESSES);
                    int playtime = highscore.getInt(TAG_PLAYTIME);
                    String timestamp = highscore.getString(TAG_TIMESTAMP);

                    Highscore a = new Highscore(score, digits, guesses, playtime, Highscore.formatter.parse(timestamp));
                    highscoreList.add(a);
                }
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
        }

        Intent broascastIntent = new Intent();
        Bundle b = new Bundle();
        b.putParcelableArrayList("highscorelist", highscoreList);
        broascastIntent.putExtras(b);
        broascastIntent.setAction("HIGHSCORE_JSON_PARSE_ACTION");
        getBaseContext().sendBroadcast(broascastIntent);

        Log.d("Service", ":servis END");
    }

    private String loadFileFromAsset(String fileName) {
        String jsonfileContent = null;
        try {
            InputStream is = getBaseContext().getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            jsonfileContent = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonfileContent;
    }

}

