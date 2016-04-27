package com.example.sqlite_ocorrencia;

import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarMainActivity extends Activity {

EditText nomeET, descET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_adicionar_main);
		
		nomeET = (EditText) findViewById(R.id.nomeET);
		descET = (EditText) findViewById(R.id.descET);
		Button bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);
	}
	
	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String nome = nomeET.getText().toString();
			String descricao = descET.getText().toString();
			
			//inserir no bd
			DBHelper dbHelper = new DBHelper(AdicionarMainActivity.this);
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			ContentValues valores = new ContentValues();
			valores.put("nome", nome);
			valores.put("descricao", descricao);
			String horarioAtual = DateFormat.getDateTimeInstance().format(new Date());
			valores.put("horarioregistro", horarioAtual);
			long idGerado = db.insert("ocorrencia", null, valores);
			dbHelper.close();
			
			Toast.makeText(AdicionarMainActivity.this, "Ocorrência salva, Id: " + idGerado, Toast.LENGTH_LONG).show();
			AdicionarMainActivity.this.finish();
		}
	};
}
