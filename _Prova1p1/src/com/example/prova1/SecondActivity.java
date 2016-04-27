package com.example.prova1;




import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
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




public class SecondActivity extends Activity {
	
	Spinner regiaoSpinner;
	//RadioButton radioBt0, radioBt1;
	EditText nomeET;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
	    regiaoSpinner = (Spinner) findViewById(R.id.regiaoSpinner);
		
		ArrayAdapter<CharSequence> regAdapter =
		ArrayAdapter.createFromResource(this,R.array.regiao_array,
		android.R.layout.simple_spinner_item);
		
		regiaoSpinner.setAdapter(regAdapter);
		
		nomeET = (EditText) findViewById(R.id.editText1);
		
		//radioBt0 = (RadioButton) findViewById(R.id.radio0);
		//radioBt1 = (RadioButton) findViewById(R.id.radio1);
		
		Button btSalvar = (Button) findViewById(R.id.button1);
		btSalvar.setOnClickListener(onBt1ClickListener);
	}
	

	
	private OnClickListener onBt1ClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
				if(validaDados()) {
					Builder alertDialogBuilder = new Builder(SecondActivity.this);
					alertDialogBuilder.setTitle("Erro");
					alertDialogBuilder.setMessage("Verificar dados");
					alertDialogBuilder.setPositiveButton("OK", null);
					alertDialogBuilder.show();
				}else{ 	
					Intent i = new Intent(SecondActivity.this, TerceiraActivity.class);
					startActivity(i);
				}
			}
	};
	
	private Boolean validaDados(){
		String regiao = regiaoSpinner.getSelectedItem().toString();
		String nome = nomeET.getText().toString();	
		if(nome==null || nome.equals("")){
			return true;
		}else if(regiao.equals("--")){
			return true;
		}
		return false;
	}
	
}
