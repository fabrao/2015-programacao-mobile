package com.example.activitylife_abrirfechar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		Button bt_fechar = (Button) findViewById(R.id.twoFecharButton);
		bt_fechar.setOnClickListener(btFecharListener);
		
		Button bt_abrirOne = (Button) findViewById(R.id.twoAbrirOneButton);
		bt_abrirOne.setOnClickListener(btAbrirOneListener);
	}
	
	private OnClickListener btFecharListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			TwoActivity.this.finish();
		}
	};
	
	private OnClickListener btAbrirOneListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(TwoActivity.this, OneActivity.class);
			startActivity(i);
		}
	};

}
