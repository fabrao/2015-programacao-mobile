package com.example.avaliacao02_02;

import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	
	Button btRecordVideo;
	VideoView videoView1;
	int videoNumber = 0;
	String videoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		
		videoView1 = (VideoView) findViewById(R.id.videoView1);
		btRecordVideo = (Button) findViewById(R.id.button1);
		btRecordVideo.setOnClickListener(btRecordVideoListener);
	}
	
	
	private OnClickListener btRecordVideoListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoArquivo());
			i.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 3);
			
			if(i.resolveActivity(VideoActivity.this.getPackageManager()) != null) {
				startActivityForResult(i, 33);
			}else {
				Toast.makeText(VideoActivity.this, "There is no app to capture video!", Toast.LENGTH_SHORT).show();
			}
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 33) {
			if (resultCode == RESULT_OK) {
				Uri recordedVideoUri = data.getData();

				MediaController controller = new MediaController(VideoActivity.this);
				videoView1.setVideoURI(recordedVideoUri);
				videoView1.setMediaController(controller);
			} else { 
				Toast.makeText(this, "Video was not recorded!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	protected Uri getCaminhoArquivo() {
		File diretorioMidia = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "avaliacao02p02");
		
		if (!diretorioMidia.exists() && !diretorioMidia.mkdirs())
			Log.d("avaliacao02p02", "error creating the file");

		videoNumber++;
		String fileName = "video" + videoNumber + ".mp4";
		videoName = diretorioMidia.getPath() + File.separator + fileName;

		return Uri.fromFile(new File(videoName));
	}
		
}
