package com.example.simpletracker;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Button bt1;
	Context context;
	LocationManager locationManager;
	LinearLayout locLayout;
	
	Location locationReferencia = new Location ("point A");
	float distanceAnterior;
	float distanceAtual;
	int cont;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//object used to refer to the original activity instance
		context = this;
		cont = 0;
		locationReferencia.setLatitude(-23.18);
		locationReferencia.setLongitude(-50.65);
		
		locLayout = (LinearLayout) findViewById(R.id.locationLayout);
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	/**
	 * Class that deals with the click on the "startTracking" button
	 */
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(i);
			}
			else {
				cont=0;
				Toast.makeText(context, "Location is enabled", Toast.LENGTH_LONG).show();
				locLayout.removeAllViews();
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationListener);
			}
		}
	};
	
	LocationListener locationListener = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		
		@Override
		public void onProviderEnabled(String provider) {
		}
		
		@Override
		public void onProviderDisabled(String provider) {
		}
		
		@Override
		public void onLocationChanged(Location location) {
			DecimalFormat df = new DecimalFormat("#.0000");
			String fLat = df.format( location.getLatitude() );
			String fLong = df.format( location.getLongitude() );
			String msg = "Latitude: " + fLat + "\nLongitude: " + fLong;
			TextView newTV = new TextView(context);
			newTV.setText(msg);
			
			
			LinearLayout newLine = new LinearLayout(context);
			newLine.setOrientation(LinearLayout.HORIZONTAL);
			Button tBt = new Button(context);
			tBt.setText("view in maps");
			ViewOnMapsListener mapsListener = new ViewOnMapsListener(location.getLatitude(), location.getLongitude());
			tBt.setOnClickListener(mapsListener);
			newLine.addView(newTV);
			newLine.addView(tBt);
			locLayout.addView(newLine, 0);
			
			if(cont==0){
				distanceAtual = location.distanceTo(locationReferencia);
				distanceAnterior = distanceAtual;
				cont = 1;
				Toast.makeText(context, "Coletada a distancia inicial da UTFPR!!", Toast.LENGTH_LONG).show();
			}else{
				distanceAtual = location.distanceTo(locationReferencia);
				if(distanceAtual > distanceAnterior)
					Toast.makeText(context, "Você está cada vez mais longe da UTFPR!!", Toast.LENGTH_LONG).show();
				
				distanceAnterior = distanceAtual;
			//Toast.makeText(context, "New location added!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	/**
	 * Internal class to deals with the click on buttons created on-the-fly
	 * 
	 * A named class was used since we need to keep a copy of 
	 * latitude and longitude.
	 *
	 */
	private class ViewOnMapsListener implements OnClickListener {
		double latitude, longitude;
		public ViewOnMapsListener(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse("geo:" + latitude + ","+longitude+"?z=16");
			Intent i = new Intent(Intent.ACTION_VIEW, uri);
			if(i.resolveActivity(context.getPackageManager()) != null)
				startActivity(i);
			else
				Toast.makeText(context, "No app can open geo locations!", Toast.LENGTH_SHORT).show();
		}
	}
}
