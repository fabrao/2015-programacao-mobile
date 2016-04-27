package com.example.usegooglemaps;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
import android.widget.Toast;

public class MainActivity extends Activity {

	private GoogleMap map;
	private Location currentLocation = null;
	private Location lastMarked = null;
	final LatLng UTFPR_CP = new LatLng(-23.1862594, -50.6570283);
	Button currBt;
	Context context;
	int markNumber = 0;
	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, locationListener);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.addMarker(new MarkerOptions().position(UTFPR_CP).title("UTFPR - CP"));
		
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(UTFPR_CP, 15));
		
		currBt = (Button) findViewById(R.id.currentButton);
		currBt.setOnClickListener(currentLocationBtListener);
	}
	
	private void addNewMark() {
		markNumber++;
		LatLng currLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
		map.addMarker(new MarkerOptions().position(currLatLng).title("Mark "+markNumber));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(currLatLng, 15));					
		Toast.makeText(context, "Current Location has been marked in the map as Mark "+markNumber+"!", Toast.LENGTH_LONG).show();
		
		lastMarked = currentLocation;
	}

	private OnClickListener currentLocationBtListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			
			if(! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(i);
			}
			else if(currentLocation != null) {
				
				if(currentLocation != lastMarked)
					addNewMark();
				else
					Toast.makeText(context, "This Location has already been marked!", Toast.LENGTH_LONG).show();
			}
			else
				Toast.makeText(context, "Current Location has not been retrieved yet. Try again!", Toast.LENGTH_LONG).show();
		}
	};
	
	LocationListener locationListener = new LocationListener() {
		@Override
		public void onLocationChanged(Location location) {
			currentLocation = location;
		}
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
		@Override
		public void onProviderEnabled(String provider) {
		}
		@Override
		public void onProviderDisabled(String provider) {
		}
	};
}
