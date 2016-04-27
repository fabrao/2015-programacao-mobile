package com.example.medias;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btTakeShowPic, btMic, btVideo, btFotoDefault, btVideoMedia06;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btTakeShowPic = (Button) findViewById(R.id.takePictureButton);
		btTakeShowPic.setOnClickListener(btTakePictureListener);

		btVideo = (Button) findViewById(R.id.videoButton);
		btVideo.setOnClickListener(btVideoListener);	
		
		btFotoDefault = (Button) findViewById(R.id.bt_fotoDefault);
		btFotoDefault.setOnClickListener(btFotoDefaultListener);
		
		btVideoMedia06 = (Button) findViewById(R.id.bt_videomedia06);
		btVideoMedia06.setOnClickListener(btVideoMedias06Listener);
	}
	
	private OnClickListener btTakePictureListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			
			//verify if there is a camera
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				Intent i = new Intent(MainActivity.this, TakeAndShowPictureActivity.class);
				startActivity(i);
			}
			else {
				Toast.makeText(MainActivity.this, "there is no camera!!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	private OnClickListener btVideoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			
			//verify if there is a camera
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) &&
					packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
				Intent i = new Intent(MainActivity.this, VideoActivity.class);
				startActivity(i);
			}
			else {
				Toast.makeText(MainActivity.this, "there is no camera and/or microphone!!", Toast.LENGTH_SHORT).show();
			}
		}
	};	
	
	private OnClickListener btFotoDefaultListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			
			//verify if there is a camera
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
				Intent i = new Intent(MainActivity.this, FotoDefaultActivity.class);
				startActivity(i);
			}
			else {
				Toast.makeText(MainActivity.this, "there is no camera!!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	private OnClickListener btVideoMedias06Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			PackageManager packageManager = MainActivity.this.getPackageManager();
			
			//verify if there is a camera
			if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) &&
					packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
				Intent i = new Intent(MainActivity.this, VideoMedias06Activity.class);
				startActivity(i);
			}
			else {
				Toast.makeText(MainActivity.this, "there is no camera and/or microphone!!", Toast.LENGTH_SHORT).show();
			}
		}
	};	
}
