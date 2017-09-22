package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();

                // Code for adding tweet
				NormalTweet newTweet = new NormalTweet("Hello");
				ImportantTweet newTweet2 = new ImportantTweet("hello", new Date());
				newTweet2.getDate();
                Log.d("Test","Before Mood");
                // Code for adding Moods
                MoodHappy newMood = new MoodHappy();
                MoodSad newMood2 = new MoodSad(new Date());
                newMood2.getDate();

                // Code for adding Moods Object into Tweet Object
                /* From my understand of the requirement. I think we need
                   to add Mood objects to Tweet Object. The function below
                   is defined in Tweet.java
                */
                Log.d("Test","Adding Mood to list");
                ArrayList<BaseMood> moodList = new ArrayList<BaseMood>();
                moodList.add(newMood);
                moodList.add(newMood);
                newTweet.addMoodList(moodList);
                //newTweet.addMood(newMood);
                //newTweet.addMood(newMood2);

                Log.d("Test","Finish adding Mood to list");
				try{
					newTweet.setMessage("Goodbye");
				}catch(TweetTooLongException e){

				}
				Log.d("Tweet",newTweet.getMessage());

				ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
				tweetList.add(newTweet);
				tweetList.add(newTweet2);

				saveInFile(text, new Date(System.currentTimeMillis()));


                finish();

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String[] tweets = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private String[] loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}