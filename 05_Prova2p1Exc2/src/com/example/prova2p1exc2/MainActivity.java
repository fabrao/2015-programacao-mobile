package com.example.prova2p1exc2;


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
		
		Button btTodas = (Button) findViewById(R.id.button1);
		btTodas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MostarTodasActivity.class);
				startActivity(i);
			}
		});
		
		Button btPorID = (Button) findViewById(R.id.button2);
		btPorID.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, MostrarPorIDActivity.class);
				startActivity(i);
			}
		});

		Button btAdd = (Button) findViewById(R.id.button3);
		btAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AddCategoriaActivity.class);
				startActivity(i);
			}
		});
	}
}
