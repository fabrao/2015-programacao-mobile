package com.example.prova2p1exc2;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MostrarPorIDActivity extends ActionBarActivity {
	Button updateBT;
	LinearLayout transLayout;
	EditText numID;
	String numeroID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostar_todas);
		numID = (EditText) findViewById(R.id.editText1);
		updateBT = (Button) findViewById(R.id.button2);
		updateBT.setOnClickListener(bt2OnClickListener);
		numeroID = numID.getText().toString();
		
		Button btVoltar = (Button) findViewById(R.id.button1);
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MostrarPorIDActivity.this.finish();
			}
		});
		
		transLayout = (LinearLayout) findViewById(R.id.CatLinearLayout);
		
	}
	
	private void updateTransactions() {
		
		transLayout.removeAllViews();
		
		String resourceURL = "http://10.0.2.2:4000/categoria/"+numeroID;
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		httpClient.get(resourceURL, new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
				
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject obj = jsonArray.getJSONObject(i);
						StringBuilder sb = new StringBuilder();
						sb.append("id: " + obj.getInt("id"));
						sb.append(" - ");
						sb.append("nome: " + obj.getString("nome"));
						sb.append(" - ");
						sb.append("desc: " + obj.getString("descricao"));
						
						TextView tv = new TextView(MostrarPorIDActivity.this);
						tv.setText(sb.toString());
						transLayout.addView(tv);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}
	
	private OnClickListener bt2OnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateTransactions();
		}
	};
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_por_id, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
