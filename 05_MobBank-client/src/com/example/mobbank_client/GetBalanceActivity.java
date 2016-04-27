package com.example.mobbank_client;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mobbank_client.entity.LogTransaction;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GetBalanceActivity extends ActionBarActivity {
	
	TextView accountTV;
	Button searchBT;
	EditText acNumberET;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_balance);
		
		accountTV = (TextView) findViewById(R.id.accountTextView);
		acNumberET = (EditText) findViewById(R.id.accountNumberEditText);
		searchBT = (Button) findViewById(R.id.searchButton);
		searchBT.setOnClickListener(btSearchOnClickListener);
	}

	private OnClickListener btSearchOnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String acNumber = acNumberET.getText().toString();
			
			String resourceURL = "http://10.0.2.2:4000/account/" + acNumber;
			AsyncHttpClient httpClient = new AsyncHttpClient();
			httpClient.get(resourceURL, new JsonHttpResponseHandler() {
				public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
					updateView(response);
				}
			});
		}
	};
	
	public void updateView(JSONObject response) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("number: " + response.getLong("number"));
			sb.append("\n");
			sb.append("Name: " + response.getString("clientName"));
			sb.append("\n");
			sb.append("Balance: " + response.getDouble("balance"));
			
			LogTransaction logTransaction = new LogTransaction(GetBalanceActivity.this);
			logTransaction.register(response.getLong("number"), "BALANCE", "viewing an account balance");						
			
			
		} catch (JSONException e) {
			sb.append("no account");
		}
		accountTV.setText(sb.toString());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_balance, menu);
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
