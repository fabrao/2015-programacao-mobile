package com.example.activity_serializable;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DadosConfirmarActivity extends Activity {
	
	Dados dados;
	TextView nomeTV, idadeTV, alturaTV, pesoTV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dados_confirmar);
		
		dados = (Dados) getIntent().getSerializableExtra("dados");
		
		nomeTV = (TextView) findViewById(R.id.NomeTextView);
		idadeTV = (TextView) findViewById(R.id.idadeTextView);
		alturaTV = (TextView) findViewById(R.id.alturaTextView);
		pesoTV = (TextView) findViewById(R.id.pesoTextView);
		
		Button btCalcular = (Button) findViewById(R.id.calcularButton);
		btCalcular.setOnClickListener(btCalcularListener);
		
		preencherTextViews();
	}

	private void preencherTextViews() {
		nomeTV.setText( dados.getNome() );
		idadeTV.setText( String.valueOf(dados.getIdade()) );
		alturaTV.setText( String.valueOf(dados.getAltura()) );
		pesoTV.setText( String.valueOf(dados.getPeso()) );
	}
	
	private OnClickListener btCalcularListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Builder builder = new Builder(DadosConfirmarActivity.this);
			builder.setTitle("IMC");
			builder.setMessage("IMC = " + dados.getIMC());
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					//voltar para a tela principal
					//fechando todas as Activities abertas previamente
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
			builder.create().show();
		}
	};
}
