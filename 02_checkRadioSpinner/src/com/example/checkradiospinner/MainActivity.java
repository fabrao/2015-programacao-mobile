package com.example.checkradiospinner;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	LinearLayout checkboxesLayout;
	RadioButton radioBt0, radioBt1, radioBt2; 
	EditText nomeET;
	Spinner regiaoSpinner;
	PreferenciaDAO dao = new PreferenciaDAO();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		checkboxesLayout = (LinearLayout) findViewById(R.id.prefsLinearLayout);
		
		preencherCheckBoxList( dao.getByFaixa(1) );
				
		regiaoSpinner = (Spinner) findViewById(R.id.regiaoSpinner);
		/*
		//preencher o Spinner dinamicamente
		ArrayList<String> reg = new ArrayList<>();
		reg.add("Sul");
		reg.add("Sudeste");
		ArrayAdapter<String> regAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, reg);
		*/
		ArrayAdapter<CharSequence> regAdapter = ArrayAdapter.createFromResource(this, R.array.regiao_array, android.R.layout.simple_spinner_item);
		regiaoSpinner.setAdapter(regAdapter);
		
		radioBt0 = (RadioButton) findViewById(R.id.radio0);
		radioBt0.setOnClickListener(onRadioClickListener);
		
		radioBt1 = (RadioButton) findViewById(R.id.radio1);
		radioBt1.setOnClickListener(onRadioClickListener);
		
		radioBt2 = (RadioButton) findViewById(R.id.radio2);
		radioBt2.setOnClickListener(onRadioClickListener);
		
		Button btGravar = (Button) findViewById(R.id.button1);
		btGravar.setOnClickListener(onBt1ClickListener);
		
		nomeET = (EditText) findViewById(R.id.nomeEditText);
	}
	
	private OnClickListener onBt1ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			StringBuilder msg = new StringBuilder();
			msg.append( nomeET.getText().toString() + "\n" );
			msg.append( regiaoSpinner.getSelectedItem() + "\n" );
			msg.append( "0: "+radioBt0.isChecked()+"; 1: "+radioBt1.isChecked()+"; 2: "+radioBt2.isChecked()+"\n" );
			msg.append("preferences: ");
			int numOfCB = checkboxesLayout.getChildCount();
			for (int i = 0; i < numOfCB; i++) {
				CheckBox cbox = (CheckBox) checkboxesLayout.getChildAt(i);
				if(cbox.isChecked()) {
					msg.append(cbox.getText() + " ");
				}
			}
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
	};
	
	private OnClickListener onRadioClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			boolean checked = ((RadioButton) v).isChecked();
			
			if(checked) {
				switch ( v.getId() ) {
				case R.id.radio0:
					preencherCheckBoxList( dao.getByFaixa(1) );
					break;
				case R.id.radio1:
					preencherCheckBoxList( dao.getByFaixa(2) );
					break;
				case R.id.radio2:
					preencherCheckBoxList( dao.getByFaixa(3) );
					break;
				}
			}
		}
	};

	private void preencherCheckBoxList(ArrayList<Preferencia> prefsByFaixa) {
		checkboxesLayout.removeAllViews();

		for(Preferencia p : prefsByFaixa) {
			CheckBox cbox = new CheckBox(MainActivity.this);
			cbox.setId( p.getId() );
			cbox.setText( p.getNome() );
			checkboxesLayout.addView(cbox);
		}
	}
}
