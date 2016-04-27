package com.example.activitylife_abrirfechar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OneActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		Button btFechar = (Button) findViewById(R.id.oneFecharButton);
		btFechar.setOnClickListener(btFecharListener);
	}
	
	private OnClickListener btFecharListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			OneActivity.this.finish();
		}
	};
}
