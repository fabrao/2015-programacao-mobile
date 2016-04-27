package com.example.quentefrio;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText latitudeET, longitudeET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		latitudeET = (EditText) findViewById(R.id.editText1);
		longitudeET = (EditText) findViewById(R.id.editText2);
		
		Button iniciarButton = (Button) findViewById(R.id.button1);
		iniciarButton.setOnClickListener(bt1Listener);
	}
	
	
	private OnClickListener bt1Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {

			Intent i = new Intent(MainActivity.this, JogoActivity.class);
			Dados dados = new Dados();
			
			double latitude = Double.parseDouble(latitudeET.getText().toString());
			double longitude = Double.parseDouble(longitudeET.getText().toString());
			
			dados.setLatitude(latitude);
			dados.setLongitude(longitude);
			i.putExtra("dados", dados);
			
			startActivity(i);
		}
	};
}
