package com.example.prova1p2;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences myPreferences = getSharedPreferences("dados", Context.MODE_PRIVATE);
		 
		
		String nome = myPreferences.getString("nome", "Sem Nome");
		String sexo = myPreferences.getString("sexo", "Não Informado");
		String idade = myPreferences.getString("idade", "Não Informado");
		
		TextView tvNome = (TextView) findViewById(R.id.tvNome);
		tvNome.setText(nome);
		TextView tvSexo = (TextView) findViewById(R.id.tvSexo);
		tvSexo.setText(sexo);
		TextView tvIdade = (TextView) findViewById(R.id.tvIdade);
		tvIdade.setText(idade);
		
		
		Button btModificar = (Button) findViewById(R.id.button1);
		btModificar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, ModificarActivity.class);
				startActivity(i);
				MainActivity.this.finish();
			}
		});
	}
}
