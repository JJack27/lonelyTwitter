package ca.ualberta.cs.lonelytwitter;
import java.util.*;
/**
 * Created by yizho on 2017/9/13.
 * Mood: Happy
 */

public class MoodHappy extends BaseMood {

    public MoodHappy(){
        super();
        super.setMood("Happy Face!");
    }

    public MoodHappy(Date date){
        super(date);
        super.setMood("Happy Face!");
    }

}
