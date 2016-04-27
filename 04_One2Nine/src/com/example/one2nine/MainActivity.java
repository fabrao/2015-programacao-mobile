package com.example.one2nine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button btGame = (Button) findViewById(R.id.startButton);
		btGame.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GameActivity.class);
				startActivity(i);
			}
		});
		
		Button btScore = (Button) findViewById(R.id.scoreButton);
		btScore.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, EscoreActivity.class);
				startActivity(i);
			}
		});
		
	}
}
