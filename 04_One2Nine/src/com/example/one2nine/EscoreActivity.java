package com.example.one2nine;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EscoreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escore);
		
		//retrieving persisted preferences
		final SharedPreferences myPreferences = getSharedPreferences("One2NinePrefs", Context.MODE_PRIVATE);
		float bestTime = myPreferences.getFloat("time1", 0);
		String playedBy = myPreferences.getString("playedBy1", "No one played before.");
		String playedIn = myPreferences.getString("playedIn1", "");
		
		float bestTime2 = myPreferences.getFloat("time2", 0);
		String playedBy2 = myPreferences.getString("playedBy2", "No one played before.");
		String playedIn2 = myPreferences.getString("playedIn2", "");
		
		float bestTime3 = myPreferences.getFloat("time3", 0);
		String playedBy3 = myPreferences.getString("playedBy3", "No one played before.");
		String playedIn3 = myPreferences.getString("playedIn3", "");
		
		
		TextView bestTimeTV = (TextView) findViewById(R.id.bestTimeTextView);
		DecimalFormat df = new DecimalFormat("#.00"); 
		bestTimeTV.setText("  " +  df.format(bestTime) );
		TextView pByTV = (TextView) findViewById(R.id.playedByTextView);
		pByTV.setText("  " +  playedBy + "\n" + playedIn + "\n");
		
		TextView bestTimeTV2 = (TextView) findViewById(R.id.bestTime2TextView);
		bestTimeTV2.setText("  " + df.format(bestTime2) );
		TextView pByTV2 = (TextView) findViewById(R.id.playedBy2TextView);
		pByTV2.setText("  " +  playedBy2 + "\n" + playedIn2 + "\n");
		
		TextView bestTimeTV3 = (TextView) findViewById(R.id.bestTime3TextView);
		bestTimeTV3.setText("  " +  df.format(bestTime3) );
		TextView pByTV3 = (TextView) findViewById(R.id.playedBy3TextView);
		pByTV3.setText("  " +  playedBy3 + "\n" + playedIn3 + "\n");
		
		Button btClear = (Button) findViewById(R.id.btClear);
		btClear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Builder alertDialogBuilder = new Builder(EscoreActivity.this);
				alertDialogBuilder.setTitle("CONFIRM");
				alertDialogBuilder.setMessage("Clear the Records?");
				alertDialogBuilder.setCancelable(false);
				//botao Sim
				alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Editor editor = myPreferences.edit();
						editor.clear();
						editor.commit();
						EscoreActivity.this.finish();
					}
				});
				//botao Nao
				alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
				AlertDialog dialog = alertDialogBuilder.create();
				dialog.show();
			}
		});
		
		Button btBack = (Button) findViewById(R.id.btBack);
		btBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EscoreActivity.this.finish();
			}
		});
	}
}
