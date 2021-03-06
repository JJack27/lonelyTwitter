package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity implements View.OnClickListener {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		Button clearButton = (Button) findViewById(R.id.clear);
        saveButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}


	// clicks for Buttions
	public void onClick(View v){
		int id = v.getId();
        Log.d("Button", "inOnClick");
        if(id == R.id.save){
			// logic for save Button
			setResult(RESULT_OK);
			String text = bodyText.getText().toString();
			tweets.add(new NormalTweet(text));
			adapter.notifyDataSetChanged();
            bodyText.setText("");   // also clear the edit text field
			saveInFile();
		}else if(id == R.id.clear){
			// logic for clear Button
			setResult(RESULT_OK);
			tweets.clear();
            bodyText.setText("");   // also clear the edit text field
			adapter.notifyDataSetChanged();
			saveInFile();
		}
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			Gson gson = new Gson();
			// read data from gson file
			Type listType = new TypeToken<ArrayList<NormalTweet>>(){}.getType();
			tweets = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		//return tweets.toArray(new String[tweets.size()]);
	}

	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			OutputStreamWriter out = new OutputStreamWriter(fos);
			Gson gson = new Gson();
			gson.toJson(tweets, out);
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
