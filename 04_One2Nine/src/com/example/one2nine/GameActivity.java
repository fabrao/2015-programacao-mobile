package com.example.one2nine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.one2nine.game.GameConfig;
import com.example.one2nine.game.Number;

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
import android.widget.EditText;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	ArrayList<Button> buttons = new ArrayList<Button>();
	GameConfig gameConfig = new GameConfig();
	List<Number> currentListOfNumbers;
	int nextNumberMustBe;
	long startTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		retrieveButtons();
		
		startNewRound();
		startTime = System.currentTimeMillis();
	}

	private void retrieveButtons() {
		Button bt1 = (Button) findViewById(R.id.btClear);
		bt1.setOnClickListener(btClickListener);
		Button bt2 = (Button) findViewById(R.id.btBack);
		bt2.setOnClickListener(btClickListener);
		Button bt3 = (Button) findViewById(R.id.button3);
		bt3.setOnClickListener(btClickListener);
		Button bt4 = (Button) findViewById(R.id.button4);
		bt4.setOnClickListener(btClickListener);
		Button bt5 = (Button) findViewById(R.id.button5);
		bt5.setOnClickListener(btClickListener);
		Button bt6 = (Button) findViewById(R.id.button6);
		bt6.setOnClickListener(btClickListener);
		Button bt7 = (Button) findViewById(R.id.button7);
		bt7.setOnClickListener(btClickListener);
		Button bt8 = (Button) findViewById(R.id.button8);
		bt8.setOnClickListener(btClickListener);
		Button bt9 = (Button) findViewById(R.id.button9);
		bt9.setOnClickListener(btClickListener);
		buttons.add(bt1);
		buttons.add(bt2);
		buttons.add(bt3);
		buttons.add(bt4);
		buttons.add(bt5);
		buttons.add(bt6);
		buttons.add(bt7);
		buttons.add(bt8);
		buttons.add(bt9);
	}
	
	private void startNewRound() {
		currentListOfNumbers = gameConfig.getNextConfiguration();
		for (int i = 0; i < buttons.size(); i++) {
			Button bt = buttons.get(i);
			bt.setBackgroundResource(android.R.color.background_light);
			bt.setText( currentListOfNumbers.get(i).getLabel() );
		}
		
		nextNumberMustBe = 1;
	}

	private OnClickListener btClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			int clickedButton = 0;
			for (int i = 0; i < buttons.size(); i++) {
				Button bt = buttons.get(i);
				if(v.getId() == bt.getId()) {
					clickedButton = i;
					break;
				}
			}
			//check value
			Number number = currentListOfNumbers.get(clickedButton);
			Button btCurrent = buttons.get(clickedButton);
			if(number.getValue() == nextNumberMustBe) {
				btCurrent.setBackgroundResource(android.R.color.holo_blue_light);
				nextNumberMustBe++;
			}
			
			if(nextNumberMustBe == 10) {
				checkEnd();
			}
		}
	};
	
	private void checkEnd() {
		if(gameConfig.hasNext()) {
			startNewRound();
		}
		else {
			endGame();
		}
	}

	private void endGame() {
		long endTime = System.currentTimeMillis();
		final float yourTime = (endTime - startTime) / 1000.0f;
		
		String msg = "Your time was " + yourTime + " seconds.";
		
		//verify if it is a new high score
		final SharedPreferences myPreferences = getSharedPreferences("One2NinePrefs", Context.MODE_PRIVATE);
		float bestTime = myPreferences.getFloat("time1", Float.MAX_VALUE);
		float secondbestTime = myPreferences.getFloat("time2", Float.MAX_VALUE);
		float thirdbestTime = myPreferences.getFloat("time3", Float.MAX_VALUE);
		
		if(yourTime < bestTime) {	//update best score
/****************************************************************************************************/	
			 if(bestTime<secondbestTime){
	            	if(secondbestTime<thirdbestTime){
		               	 Editor editor = myPreferences.edit();
		   	        	 editor.putString("playedBy3", myPreferences.getString("playedBy2", ""));	//improve - user can write a name
		   	        	 editor.putFloat("time3", myPreferences.getFloat("time2", 0));
		   	     		 editor.putString("playedIn3", myPreferences.getString("PlayedIn2", ""));
		           		 editor.commit();
	            	}
	            	 Editor editor = myPreferences.edit();
		        	 editor.putString("playedBy2", myPreferences.getString("playedBy1", ""));	//improve - user can write a name
		        	 editor.putFloat("time2", myPreferences.getFloat("time1", 0));
		     		 editor.putString("playedIn2", myPreferences.getString("PlayedIn1", ""));
	        		 editor.commit();
	            }
			 
        	AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
        	alert.setTitle("CONGRATULATIONS - New Record"); //Set Alert dialog title here
        	alert.setMessage("Enter Your Name"); //Message here
            final EditText input = new EditText(GameActivity.this);
            alert.setView(input);           
        	alert.setPositiveButton("Save Name", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
		        	String name = input.getEditableText().toString();
		        	 Editor editor = myPreferences.edit();
		        	 editor.putString("playedBy1", name);	//improve - user can write a name
		        	 editor.putFloat("time1", yourTime);
		     		 String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     		 editor.putString("playedIn1", currentDate);
	        		 editor.commit();
		        	 GameActivity.this.finish();
	        	}
	        });
        	
        	alert.setNegativeButton("Anonymous", new DialogInterface.OnClickListener() {
        	  public void onClick(DialogInterface dialog, int whichButton) {
        		  Editor editor = myPreferences.edit();
        		  editor.putString("playedBy1", "Anonymous");	//improve - user can write a name
		          editor.putFloat("time1", yourTime);
		     	  String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     	  editor.putString("playedIn1", currentDate);
        		  editor.commit();
        		  dialog.cancel();
        		  GameActivity.this.finish();
        	  }
        }); 
        	AlertDialog alertDialog = alert.create();
        	alertDialog.show();
/****************************************************************************************************/
		}else if (yourTime < secondbestTime){
/****************************************************************************************************/		
        	if(secondbestTime<thirdbestTime){
              	 Editor editor = myPreferences.edit();
  	        	 editor.putString("playedBy3", myPreferences.getString("playedBy2", ""));	//improve - user can write a name
  	        	 editor.putFloat("time3", myPreferences.getFloat("time2", 0));
  	     		 editor.putString("playedIn3", myPreferences.getString("PlayedIn2", ""));
          		 editor.commit();
        	}
			
        	AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
        	alert.setTitle("CONGRATULATIONS - New SecondRecord"); //Set Alert dialog title here
        	alert.setMessage("Enter Your Name"); //Message here
            final EditText input = new EditText(GameActivity.this);
            alert.setView(input);
        	alert.setPositiveButton("Save Name", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
		        	String name = input.getEditableText().toString();
		        	 Editor editor = myPreferences.edit();
		        	 editor.putString("playedBy2", name);	//improve - user can write a name
		        	 editor.putFloat("time2", yourTime);
		     		 String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     		 editor.putString("playedIn2", currentDate);
	        		 editor.commit();
		        	 GameActivity.this.finish();
	        	}
	        });
        	
        	alert.setNegativeButton("Anonymous", new DialogInterface.OnClickListener() {
        	  public void onClick(DialogInterface dialog, int whichButton) {
        		  Editor editor = myPreferences.edit();
        		  editor.putString("playedBy2", "Anonymous");	//improve - user can write a name
		          editor.putFloat("time2", yourTime);
		     	  String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     	  editor.putString("playedIn2", currentDate);
        		  editor.commit();
        		  dialog.cancel();
        		  GameActivity.this.finish();
        	  }
        }); 
        	AlertDialog alertDialog = alert.create();
        	alertDialog.show();
/****************************************************************************************************/			
		}else if (yourTime < thirdbestTime){
/****************************************************************************************************/				
        	AlertDialog.Builder alert = new AlertDialog.Builder(GameActivity.this);
        	alert.setTitle("CONGRATULATIONS - New ThirdRecord"); //Set Alert dialog title here
        	alert.setMessage("Enter Your Name"); //Message here
            final EditText input = new EditText(GameActivity.this);
            alert.setView(input);
 
        	alert.setPositiveButton("Save Name", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int whichButton) {
		        	String name = input.getEditableText().toString();
		        	 Editor editor = myPreferences.edit();
		        	 editor.putString("playedBy3", name);	//improve - user can write a name
		        	 editor.putFloat("time3", yourTime);
		     		 String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     		 editor.putString("playedIn3", currentDate);
	        		 editor.commit();
		        	 GameActivity.this.finish();
	        	}
	        });
        	
        	alert.setNegativeButton("Anonymous", new DialogInterface.OnClickListener() {
        	  public void onClick(DialogInterface dialog, int whichButton) {
        		  Editor editor = myPreferences.edit();
        		  editor.putString("playedBy3", "Anonymous");	//improve - user can write a name
		          editor.putFloat("time3", yourTime);
		     	  String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		     	  editor.putString("playedIn3", currentDate);
        		  editor.commit();
        		  dialog.cancel();
        		  GameActivity.this.finish();
        	  }
        }); 
        	AlertDialog alertDialog = alert.create();
        	alertDialog.show();
/****************************************************************************************************/	
		}
	}
}
