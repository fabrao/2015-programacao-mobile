package com.example.mobbank_client;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowAllTransactionsActivity extends ActionBarActivity {
	Button updateBT;
	LinearLayout transLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_transactions);
		
		updateBT = (Button) findViewById(R.id.button1);
		updateBT.setOnClickListener(bt1OnClickListener);
		
		transLayout = (LinearLayout) findViewById(R.id.transactionsLinearLayout);
		updateTransactions();
	}
	
	private void updateTransactions() {
		
		transLayout.removeAllViews();
		
		String resourceURL = "http://10.0.2.2:4000/transaction";
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		httpClient.get(resourceURL, new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
				
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject obj = jsonArray.getJSONObject(i);
						StringBuilder sb = new StringBuilder();
						sb.append("number: " + obj.getLong("accountNumber"));
						sb.append(" - ");
						sb.append("type: " + obj.getString("type"));
						sb.append(" - ");
						sb.append("desc: " + obj.getString("description"));
						sb.append(" - ");
						sb.append("date: " + obj.getString("date"));
						
						TextView tv = new TextView(ShowAllTransactionsActivity.this);
						tv.setText(sb.toString());
						transLayout.addView(tv);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}
	
	private OnClickListener bt1OnClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateTransactions();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_all_transactions, menu);
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
