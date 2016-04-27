package com.example.medias;

import java.io.File;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class VideoMedias06Activity extends Activity {
	Button btRecordVideo;
	TextView tv1;
	VideoView videoView1;
	int videoNumber = 0;
	String videoName;
	
	Context context;
	LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_medias06);
		
		context = this;
		
		tv1 = (TextView) findViewById(R.id.textView2);
		videoView1 = (VideoView) findViewById(R.id.videoView1);
		btRecordVideo = (Button) findViewById(R.id.button1);
		btRecordVideo.setOnClickListener(btRecordVideoListener);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	}
	
	private OnClickListener btRecordVideoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoArquivo());
			//i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0-low | 1-high);
			//i.putExtra(MediaStore.EXTRA_SIZE_LIMIT, value in bytes - 1mb -> 1024*1024);
			i.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);	//in seconds
			
			//verify if there is some app that can deal with this intent
			if(i.resolveActivity(VideoMedias06Activity.this.getPackageManager()) != null) {
				startActivityForResult(i, 33);
			}
			else {
				Toast.makeText(VideoMedias06Activity.this, "There is no app to capture video!", Toast.LENGTH_SHORT).show();
			}
		}
	};

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 33) {
			if (resultCode == RESULT_OK) {
				Uri recordedVideoUri = data.getData();
				tv1.setText("Video was recorded in " + recordedVideoUri.toString());
				MediaController controller = new MediaController(VideoMedias06Activity.this);
				videoView1.setVideoURI(recordedVideoUri);
				videoView1.setMediaController(controller);
				
				
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
					
					Toast.makeText(context, "Coordenadas do Video \n" + msg, Toast.LENGTH_LONG).show();
				
				}
			} 
			else { 
				Toast.makeText(this, "Video was not recorded!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	protected Uri getCaminhoArquivo() {
		File diretorioMidia = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "medias06");
		
		if (!diretorioMidia.exists() && !diretorioMidia.mkdirs())
			Log.d("medias06", "error creating the file");

		videoNumber++;
		String fileName = "video" + videoNumber + ".mp4";
		videoName = diretorioMidia.getPath() + File.separator + fileName;

		return Uri.fromFile(new File(videoName));
	}
	
}
