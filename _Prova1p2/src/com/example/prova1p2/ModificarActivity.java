package com.example.prova1p2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class ModificarActivity extends Activity {
	EditText etxNome;
	EditText etxIdade;
	RadioButton radioMasc, radioFem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modificar);
		
		radioMasc = (RadioButton) findViewById(R.id.radio0);
		radioFem = (RadioButton) findViewById(R.id.radio1);

		
		etxNome = (EditText) findViewById(R.id.etxNome);
		etxIdade = (EditText) findViewById(R.id.etxIdade);
		
		
		Button btSalvar = (Button) findViewById(R.id.button1);
		btSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String novoNome = etxNome.getText().toString();
				String novaIdade = etxIdade.getText().toString();
				
				SharedPreferences myPreferences = getSharedPreferences("dados", Context.MODE_PRIVATE);
				
				Editor editor = myPreferences.edit();
  	        	editor.putString("nome", novoNome);	
  	        	if(radioMasc.isChecked()){
  	        		editor.putString("sexo", "Masculino");
  	        	}else if(radioFem.isChecked()){
  	        		editor.putString("sexo", "Feminino");
  	        	}
  	        	editor.putString("idade", novaIdade);
          		editor.commit();
				
          		Intent i = new Intent(ModificarActivity.this, MainActivity.class);
				startActivity(i);
				ModificarActivity.this.finish();
			}
		});
	}
}
