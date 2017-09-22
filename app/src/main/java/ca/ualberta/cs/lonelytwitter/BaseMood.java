package ca.ualberta.cs.lonelytwitter;

import android.os.SystemClock;

import java.util.Date;

/**
 * Created by yizho on 2017/9/13.
 */

public abstract class BaseMood {
    private Date date;
    private String currentMood;

    // constructor 1: setting date to default
    public BaseMood(){
        this.date = new Date(System.currentTimeMillis());
    }

    public BaseMood(Date date){
        this.date = date;
    }

    public Date getDate(){
        return this.date;
    };

    public void setDate(Date date){
        this.date = date;
    };

    public void setMood(String mood){
        currentMood = mood;
    }

    public String getMood(){
        return this.currentMood;
    }

}
