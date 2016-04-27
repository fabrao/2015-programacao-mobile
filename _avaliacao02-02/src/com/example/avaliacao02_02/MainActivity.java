package com.example.avaliacao02_02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(i);
			}else{
				Intent i = new Intent(MainActivity.this, GPSActivity.class);
				startActivity(i);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		 if (id == R.id.item1) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				Intent i = new Intent(MainActivity.this, FotoActivity.class);
				startActivity(i);
			}else {
				Toast.makeText(MainActivity.this, "there is no camera!!", Toast.LENGTH_SHORT).show();
			}
			return true;
			
		}else if(id == R.id.item2) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) &&
				packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
				Intent i = new Intent(MainActivity.this, VideoActivity.class);
				startActivity(i);
			}else {
				Toast.makeText(MainActivity.this, "there is no camera and/or microphone!!", Toast.LENGTH_SHORT).show();
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
