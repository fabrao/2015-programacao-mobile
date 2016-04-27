package com.example.quentefrio;



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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class JogoActivity extends Activity {
	Dados dados;
	double latitudeA, longitudeA;
	
	Location locationA = new Location ("point A");
	Location locationB = new Location ("point B");
	
	Button bt1;
	Context context;
	LocationManager locationManager;
	LinearLayout locLayout;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogo);
		
		dados = (Dados) getIntent().getSerializableExtra("dados");
		
		latitudeA = dados.getLatitude();
		longitudeA = dados.getLongitude();
		
		locationA.setLatitude(latitudeA);
		locationA.setLongitude(longitudeA);
		
		context = this;
		
		locLayout = (LinearLayout) findViewById(R.id.locationLayout);
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
				}
				else {
					Toast.makeText(context, "Location is enabled", Toast.LENGTH_LONG).show();
					locLayout.removeAllViews();
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, locationListener);
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
			locationB.setLatitude(location.getLatitude());
			locationB.setLongitude(location.getLongitude());
			
			float distance = locationA.distanceTo(locationB);
			
			TextView newTV = new TextView(context);
			LinearLayout newLine = new LinearLayout(context);
			newLine.setOrientation(LinearLayout.HORIZONTAL);
			
			if(distance>5.0){
				newTV.setText("Frio:  " + distance);
			}else{
				newTV.setText("Quente:  " + distance);
			}
			newLine.addView(newTV);
			locLayout.addView(newLine, 0);
		}
	};
}
