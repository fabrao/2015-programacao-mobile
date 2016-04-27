package com.example.prova1;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button btExQ2 = (Button) findViewById(R.id.button1);
		btExQ2.setOnClickListener(onBt1ClickListener);
		
		Button btQ3 = (Button) findViewById(R.id.button2);
		btQ3.setOnClickListener(onBt2ClickListener);
	}
	
	
	private OnClickListener onBt1ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "Questão 2 realizada com sucesso!!!", Toast.LENGTH_SHORT).show();	
		}
	};		
	
	private OnClickListener onBt2ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = new Intent(MainActivity.this, SecondActivity.class);
			startActivity(i);
		}
	};	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(MainActivity.this, "Questão 2 realizada com sucesso!!!", Toast.LENGTH_SHORT).show();	
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
