package com.example.medias;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TakeAndShowPictureActivity extends Activity {
	Button bt1;
	int fotoNumber = 0;
	String photoName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_and_show_picture);

		bt1 = (Button) findViewById(R.id.shotButton);
		bt1.setOnClickListener(bt1Listener);
	}

	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(MediaStore.EXTRA_OUTPUT, getCaminhoArquivo());
			
			if(i.resolveActivity(TakeAndShowPictureActivity.this.getPackageManager()) != null) {
				startActivityForResult(i, 34);
			}
			else {
				Toast.makeText(TakeAndShowPictureActivity.this, "There is no app to capture image!", Toast.LENGTH_SHORT).show();
			}
			
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 34) {
			if (resultCode == RESULT_OK) {
				Uri takenPhotoUri = Uri.fromFile(new File(photoName));
				Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
				ImageView ivPreview = (ImageView) findViewById(R.id.imageView1);
				ivPreview.setImageBitmap(takenImage);
			} 
			else { 
				Toast.makeText(this, "Picture was not taken!", Toast.LENGTH_SHORT).show();
			}
		}
	}

	protected Uri getCaminhoArquivo() {
		File diretorioMidia = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "medias06");
		

		if (!diretorioMidia.exists() && !diretorioMidia.mkdirs())
			Log.d("medias06", "error creating the file");

		fotoNumber++;
		String fileName = "foto" + fotoNumber + ".jpg";
		photoName = diretorioMidia.getPath() + File.separator + fileName;

		return Uri.fromFile(new File(photoName));
	}
}
