package com.starikcetin.ctis487.guessthenumber;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Highscore implements Parcelable {
    private int id, score, digitCount, guessCount, playTime;
    private Date timestamp;
    static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Highscore(int score, int digitCount, int guessCount, int playTime, Date timestamp) {
        this.score = score;
        this.digitCount = digitCount;
        this.guessCount = guessCount;
        this.playTime = playTime;
        this.timestamp = timestamp;
    }

    public Highscore(int id, int score, int digitCount, int guessCount, int playTime, Date timestamp) {
        this.id = id;
        this.score = score;
        this.digitCount = digitCount;
        this.guessCount = guessCount;
        this.playTime = playTime;
        this.timestamp = timestamp;
    }

    protected Highscore(Parcel in) {
        score = in.readInt();
        digitCount = in.readInt();
        guessCount = in.readInt();
        playTime = in.readInt();
        timestamp = new Date();
        try {
            timestamp = formatter.parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(score);
        dest.writeInt(digitCount);
        dest.writeInt(guessCount);
        dest.writeInt(playTime);
        dest.writeString(formatter.format(timestamp));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Highscore> CREATOR = new Creator<Highscore>() {
        @Override
        public Highscore createFromParcel(Parcel in) {
            return new Highscore(in);
        }

        @Override
        public Highscore[] newArray(int size) {
            return new Highscore[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDigitCount() {
        return digitCount;
    }

    public void setDigitCount(int digitCount) {
        this.digitCount = digitCount;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
