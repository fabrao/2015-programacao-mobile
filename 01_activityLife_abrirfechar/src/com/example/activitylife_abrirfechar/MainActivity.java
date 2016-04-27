package com.example.activitylife_abrirfechar;

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
        
        Button bt_one = (Button) findViewById(R.id.abrirOneButton);
        bt_one.setOnClickListener(btOneListener);
        
        Button bt_two = (Button) findViewById(R.id.abrirTwoButton);
        bt_two.setOnClickListener(btTwoListener);

        Button bt_fechar = (Button) findViewById(R.id.fecharButton);
        bt_fechar.setOnClickListener(btFecharListener);

    }
	
	private OnClickListener btOneListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, OneActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener btTwoListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, TwoActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener btFecharListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			MainActivity.this.finish();
		}
	};
}
