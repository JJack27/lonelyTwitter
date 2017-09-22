package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yizho on 2017/9/13.
 * Mood: Sad
 */

public class MoodSad extends BaseMood {
    public MoodSad(){
        super();
        super.setMood("Sad Face!");
    }

    public MoodSad(Date date){
        super(date);
        super.setMood("Sad Face!");
    }

}
