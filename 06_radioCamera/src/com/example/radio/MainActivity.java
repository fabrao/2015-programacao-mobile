package com.example.radio;



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
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	RadioButton radioBt0, radioBt1;
	int fotoNumber = 0;
	String photoName;
	int videoNumber = 0;
	String videoName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		radioBt0 = (RadioButton) findViewById(R.id.radio0);
		radioBt1 = (RadioButton) findViewById(R.id.radio1);
		
		Button btGravar = (Button) findViewById(R.id.button1);
		btGravar.setOnClickListener(onBt1ClickListener);
	}
	
	private OnClickListener onBt1ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			//RADIO FOTO
			if(radioBt0.isChecked()){ 
				Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoFoto());
				
				if(i.resolveActivity(MainActivity.this.getPackageManager()) != null) {
					startActivityForResult(i, 34);
				}
				else {
					Toast.makeText(MainActivity.this, "There is no app to capture image!", Toast.LENGTH_SHORT).show();
				}	
			//RADIO VIDEO	
			}else if(radioBt1.isChecked()){
				Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoVideo());
				i.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);	//in seconds
				
				if(i.resolveActivity(MainActivity.this.getPackageManager()) != null) {
					startActivityForResult(i, 33);
				}
				else {
					Toast.makeText(MainActivity.this, "There is no app to capture video!", Toast.LENGTH_SHORT).show();
				}
			}
		}
	};
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 34) {
			if (resultCode == RESULT_OK) {
				Uri takenPhotoUri = Uri.fromFile(new File(photoName));
				Toast.makeText(this, "Foto salvo em: " + takenPhotoUri.toString(), Toast.LENGTH_LONG).show();
			} 
			else { 
				Toast.makeText(this, "Picture was not taken!", Toast.LENGTH_SHORT).show();
			}
		}else if(requestCode == 33){
			if (resultCode == RESULT_OK) {
				Uri recordedVideoUri = data.getData();
				Toast.makeText(this, "Video salvo em: " + recordedVideoUri.toString(), Toast.LENGTH_LONG).show();
				
			}else{
				Toast.makeText(this, "Video was not recorder!", Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
	protected Uri getCaminhoFoto() {
		File diretorioFoto = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "radioCamera");
		
		if (!diretorioFoto.exists() && !diretorioFoto.mkdirs())
			Log.d("radioCamera", "error creating the file");

		fotoNumber++;
		String fileName = "foto" + fotoNumber + ".jpg";
		photoName = diretorioFoto.getPath() + File.separator + fileName;
		
		return Uri.fromFile(new File(photoName));
	}
	
	
	protected Uri getCaminhoVideo() {
		File diretorioVideo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), "radioCamera");
		
		if (!diretorioVideo.exists() && !diretorioVideo.mkdirs())
			Log.d("radioCamera", "error creating the file");

		videoNumber++;
		String fileName = "video" + videoNumber + ".mp4";
		videoName = diretorioVideo.getPath() + File.separator + fileName;

		return Uri.fromFile(new File(videoName));
	}
}
