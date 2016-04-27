package com.example.medias;




import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class FotoDefaultActivity extends Activity {

	Button bt1;
	int fotoNumber = 0;
	String photoName;
	ImageView nImageView;
	
	Context context;
	LocationManager locationManager;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foto_default);
		
		context = this;
		
		nImageView = (ImageView) findViewById(R.id.imageView1);
		
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
	}
	
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			//i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoArquivo());

			if(i.resolveActivity(FotoDefaultActivity.this.getPackageManager()) != null) {
				startActivityForResult(i, 34);
			}
			else {
				Toast.makeText(FotoDefaultActivity.this, "There is no app to capture image!", Toast.LENGTH_SHORT).show();
			}
		}
	};

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		if(requestCode == 34 && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			Bitmap imageBitmap = (Bitmap) extras.get("data");
			nImageView.setImageBitmap(imageBitmap);
			
			if(! locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(i);
			}
			else {
				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					
				DecimalFormat df = new DecimalFormat("#.0000");
				String fLat = df.format( location.getLatitude() );
				String fLong = df.format( location.getLongitude() );
				String msg = "Latitude: " + fLat + "\nLongitude: " + fLong;
				
				Toast.makeText(context, "Coordenadas da Foto \n" + msg, Toast.LENGTH_LONG).show();
			
			}
		}
	}

}
