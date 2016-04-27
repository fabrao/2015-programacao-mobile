package com.example.mobbank_client;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mobbank_client.entity.LogTransaction;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountActivity extends ActionBarActivity {
	
	EditText nameET, balanceET;
	Button bt1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_account);
		
		nameET = (EditText) findViewById(R.id.nameEditText);
		balanceET = (EditText) findViewById(R.id.balanceEditText);
		bt1 = (Button) findViewById(R.id.searchButton);
		bt1.setOnClickListener(onClickButton);
	}
	
	private OnClickListener onClickButton = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String tName = nameET.getText().toString();
			double tValue = Double.parseDouble( balanceET.getText().toString() );
			
			String resourceURL = "http://10.0.2.2:4000/account";
			AsyncHttpClient httpClient = new AsyncHttpClient();
			
			//create Json object
			JSONObject params = new JSONObject();
			try {
				params.put("clientName", tName);
				params.put("balance", tValue);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			
			StringEntity entity = null;
			try {
				entity = new StringEntity(params.toString());
				//indicate that the message sent is a json file
				entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));	 
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			httpClient.post(CreateAccountActivity.this, resourceURL, entity, "application/json", new JsonHttpResponseHandler() {
				public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
					showSuccess(response);
				};
			});
		}
	};
	
	public void showSuccess(JSONObject response) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CreateAccountActivity.this);
		
		long number;
		try {
			number = response.getLong("number");
		} catch (JSONException e) {
			number = -1;
		}
		
		LogTransaction logTransaction = new LogTransaction(CreateAccountActivity.this);
		logTransaction.register(number, "CREATE", "creating a new account");
		
		dialogBuilder.setMessage("Account created! Number: " + number);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				CreateAccountActivity.this.finish();
			}
		});
		dialogBuilder.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_account, menu);
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
