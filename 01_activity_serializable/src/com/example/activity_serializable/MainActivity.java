package com.example.activity_serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText nomeET, idadeET, alturaET, pesoET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		nomeET = (EditText) findViewById(R.id.nomeEditText);
		idadeET = (EditText) findViewById(R.id.idadeEditText);
		alturaET = (EditText) findViewById(R.id.alturaEditText);
		pesoET = (EditText) findViewById(R.id.pesoEditText);
		
		Button calcularButton = (Button) findViewById(R.id.button1);
		calcularButton.setOnClickListener(bt1Listener);
	}
	
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, DadosConfirmarActivity.class);

			Dados dados = new Dados();
			dados.setNome( nomeET.getText().toString() );
			int tIdade = Integer.parseInt( idadeET.getText().toString() );
			dados.setIdade(tIdade);
			double tAlt = Double.parseDouble( alturaET.getText().toString() );
			dados.setAltura(tAlt);
			double tPeso = Double.parseDouble( pesoET.getText().toString() );
			dados.setPeso(tPeso);
			i.putExtra("dados", dados);
			
			startActivity(i);
		}
	};
}
