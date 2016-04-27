package com.example.mobbank_client;

import org.apache.http.Header;

import com.example.mobbank_client.entity.LogTransaction;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CloseAccountActivity extends ActionBarActivity {

	EditText accountET;
	Button bt1;
	String acNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_close_account);
		
		accountET = (EditText) findViewById(R.id.acNumberEditText);
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);		
	}

	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			acNumber = accountET.getText().toString();
			String resourceURL = "http://10.0.2.2:4000/account/" + acNumber;
			
			AsyncHttpClient httpClient = new AsyncHttpClient();
			httpClient.delete(resourceURL, new TextHttpResponseHandler() {
				@Override
				public void onSuccess(int status, Header[] headers, String responseString) {
					if(responseString != null && responseString.equals("account removed")) {
						LogTransaction logTransaction = new LogTransaction(CloseAccountActivity.this);
						logTransaction.register(Long.valueOf(acNumber), "CLOSE", "closing an account");						
						
						Toast.makeText(CloseAccountActivity.this, "successful account closed!", Toast.LENGTH_SHORT).show();
						CloseAccountActivity.this.finish();
					}
					else {
						Toast.makeText(CloseAccountActivity.this, "Error in the closing: " + responseString, Toast.LENGTH_SHORT).show();						
					}
				}
				
				@Override
				public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
					// TODO Auto-generated method stub
				}
			});

		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.close_account, menu);
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
