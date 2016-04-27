package com.example.sharedpreferences_cliques;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	int numCliques;
	TextView textView1;
	Button button1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences preferences = getSharedPreferences("cliques", Context.MODE_PRIVATE);
		numCliques = preferences.getInt("num", 0);
		
		textView1 = (TextView) findViewById(R.id.textView1);
		button1 = (Button) findViewById(R.id.button1);
		
		textView1.setText("O botão foi clicado " + numCliques + " vezes!!");
		button1.setOnClickListener(bt1OnclickListener);
	}
	
	private OnClickListener bt1OnclickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			SharedPreferences preferences = getSharedPreferences("cliques", Context.MODE_PRIVATE);
			numCliques++;
			Editor editor = preferences.edit();
			editor.putInt("num", numCliques);
			editor.commit();
			
			textView1.setText("O botão foi clicado " + numCliques + " vezes!!");
		}
	};
}
