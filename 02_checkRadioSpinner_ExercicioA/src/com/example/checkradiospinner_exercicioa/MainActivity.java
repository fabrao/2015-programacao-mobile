package com.example.checkradiospinner_exercicioa;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	Spinner estadoSpinner;
	Spinner cidadeSpinner;
	ArrayAdapter<String> cidadesAdapter;
	ArrayList<String> cidades = new ArrayList<String>();
	CidadeDao dao = new CidadeDao();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		estadoSpinner = (Spinner) findViewById(R.id.estadoSpinner);
		ArrayAdapter<CharSequence> estadoAdapter = ArrayAdapter.createFromResource(this, R.array.estados_array, android.R.layout.simple_spinner_item);
		estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		estadoSpinner.setAdapter(estadoAdapter);
		estadoSpinner.setOnItemSelectedListener(onItemSelectedEstadoListener);
		
		cidadeSpinner = (Spinner) findViewById(R.id.cidadeSpinner);
		cidadesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cidades);
		cidadeSpinner.setAdapter(cidadesAdapter);
	}
	
	private OnItemSelectedListener onItemSelectedEstadoListener = new OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			cidades.clear();
			ArrayList<String> retCidades = dao.getCidadeByEstado(estadoSpinner.getSelectedItem().toString());
			cidades.addAll(retCidades);
			cidadesAdapter.notifyDataSetChanged();
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	};
}
