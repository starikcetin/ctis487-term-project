package com.starikcetin.ctis487.guessthenumber;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;

public class HighscoreDB {
    public static String TABLE_NAME = "highscores";
    public static String FIELD_ID = "id";
    public static String FIELD_SCORE = "score";
    public static String FIELD_DIGITS = "digits";
    public static String FIELD_GUESSES = "guesses";
    public static String FIELD_PLAYTIME = "playtime";
    public static String FIELD_TIMESTAMP = "timestamp";

    public static String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " ( " + FIELD_ID + " INTEGER, " + FIELD_SCORE + " INTEGER, " + FIELD_DIGITS + " INTEGER, " + FIELD_GUESSES + " INTEGER, " + FIELD_PLAYTIME + " INTEGER, " + FIELD_TIMESTAMP + " TEXT, PRIMARY KEY(" + FIELD_ID + " AUTOINCREMENT))";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;

    public static ArrayList<Highscore> getAllHighscores(DBHelper dbHelper) {
        Highscore anItem;
        ArrayList<Highscore> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount() + ",  " + cursor.getColumnCount());
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int score = cursor.getInt(1);
            int digits = cursor.getInt(2);
            int guesses = cursor.getInt(3);
            int playtime = cursor.getInt(4);
            String timestamp = cursor.getString(5);
            anItem = null;
            try {
                anItem = new Highscore(score, digits, guesses, playtime, Highscore.formatter.parse(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS", data.toString());
        return data;
    }

    public static boolean insert(DBHelper dbHelper, Highscore highscore) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_SCORE, highscore.getScore());
        contentValues.put(FIELD_DIGITS, highscore.getDigitCount());
        contentValues.put(FIELD_GUESSES, highscore.getGuessCount());
        contentValues.put(FIELD_PLAYTIME, highscore.getPlayTime());
        contentValues.put(FIELD_TIMESTAMP, Highscore.formatter.format(highscore.getTimestamp()));

        boolean res = dbHelper.insert(TABLE_NAME, contentValues);
        return res;
    }
}
