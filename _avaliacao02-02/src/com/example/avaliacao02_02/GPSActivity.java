package com.example.avaliacao02_02;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GPSActivity extends Activity {
	
	Button bt1;
	TextView equadorTV, meridianoTV, latitudeTV, longitudeTV;
	Location locationA = new Location ("point A");
	
	LocationManager locationManager;
	
	double lati, longi;
	String latitude, longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		equadorTV = (TextView) findViewById(R.id.equadorTV);
		meridianoTV = (TextView) findViewById(R.id.meridianoTV);
		latitudeTV = (TextView) findViewById(R.id.latitudeTV);
		longitudeTV = (TextView) findViewById(R.id.longitudeTV);

		bt1 = (Button) findViewById(R.id.button1);
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
				//Toast.makeText(MainActivity.this, "Location is enabled", Toast.LENGTH_LONG).show();
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
				
				//meridianoTV.setText(latitude);
				//equadorTV.setText(latitude);
				

			}
		}
	};
	
	
	LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			locationA.setLatitude(location.getLatitude());
			locationA.setLongitude(location.getLongitude());
			
			lati = locationA.getLatitude();
			longi = locationA.getLongitude();
			
			latitude = Double.toString(lati);
			longitude = Double.toString(longi);
			
			if(lati > 0){
				equadorTV.setText("a Norte da");
			}else if(lati < 0){
				equadorTV.setText("a Sul da");
			}else{
				equadorTV.setText("Sobre a");
			}
			
			if(longi > 0){
				meridianoTV.setText("a Oeste da");
			}else if(longi < 0){
				meridianoTV.setText("a Leste da");
			}else{
				meridianoTV.setText("Sobre a");
			}
			
			latitudeTV.setText(latitude);
			longitudeTV.setText(longitude);
		}
	};
}
