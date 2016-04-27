package com.example.sqlite_ocorrencia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(bt1Listener);
		
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(bt2Listener);           
	}
	
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, AdicionarMainActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener bt2Listener = new OnClickListener() {	
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, VisualizarMainActivity.class);
			startActivity(i);  
		}
	};
}
