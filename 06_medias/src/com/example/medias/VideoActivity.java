package com.example.medias;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	Button btRecordVideo;
	TextView tv1;
	VideoView videoView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		tv1 = (TextView) findViewById(R.id.textView1);
		videoView1 = (VideoView) findViewById(R.id.videoView1);
		btRecordVideo = (Button) findViewById(R.id.recVideoButton);
		btRecordVideo.setOnClickListener(btRecordVideoListener);
	}
	
	private OnClickListener btRecordVideoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			//i.putExtra(MediaStore.EXTRA_OUTPUT, "some directory");
			//i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0-low | 1-high);
			//i.putExtra(MediaStore.EXTRA_SIZE_LIMIT, value in bytes - 1mb -> 1024*1024);
			i.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);	//in seconds
			
			//verify if there is some app that can deal with this intent
			if(i.resolveActivity(VideoActivity.this.getPackageManager()) != null) {
				startActivityForResult(i, 33);
			}
			else {
				Toast.makeText(VideoActivity.this, "There is no app to capture video!", Toast.LENGTH_SHORT).show();
			}
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 33) {
			if (resultCode == RESULT_OK) {
				Uri recordedVideoUri = data.getData();
				tv1.setText("Video was recorded in " + recordedVideoUri.toString());
				MediaController controller = new MediaController(VideoActivity.this);
				videoView1.setVideoURI(recordedVideoUri);
				videoView1.setMediaController(controller);
			} 
			else { 
				Toast.makeText(this, "Video was not recorded!", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
