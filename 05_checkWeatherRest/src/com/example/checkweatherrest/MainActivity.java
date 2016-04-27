package com.example.checkweatherrest;

import java.text.DecimalFormat;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView connectedTV, weatherTV;
	Button bt1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		connectedTV = (TextView) findViewById(R.id.conectedTextView);
		weatherTV = (TextView) findViewById(R.id.weatherTextView);
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);
	}

	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (isNetworkAvailable())
				connectedTV.setText("yes");
			else
				connectedTV.setText("no");

			updateWeather();
		}
	};

	private void updateWeather() {
		String resourceURL = "http://api.openweathermap.org/data/2.5/weather?q=Londrina";
		AsyncHttpClient httpClient = new AsyncHttpClient();
		httpClient.setTimeout(20*1000);
		httpClient.setProxy("10.20.10.50", 3128, "a1162853", "margarida666");
	
		httpClient.get(resourceURL, new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
				String weatherString = "";
				try {
					//see http://openweathermap.org/weather-data#current
					//for the JSON file format
					double temp = jsonObject.getJSONObject("main").getDouble("temp");
					temp = temp - 273.15;
					DecimalFormat df = new DecimalFormat("#.00"); 
					weatherString = df.format(temp) + " C";
				} catch (JSONException e) {
					weatherString = "error analyzing the json file.";
				}
				weatherTV.setText(weatherString);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				weatherTV.setText("error accessing website.");
			}
		});

	}

	/**
	 * verify if it is connected in some network
	 * 
	 * @return
	 */
	private boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}

}