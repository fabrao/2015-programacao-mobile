package com.example.mobbank_client;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mobbank_client.entity.Account;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ShowAllAccountsActivity extends ActionBarActivity {
	
	Button bt1;
	ListView acListView;
	ArrayList<Account> accounts = new ArrayList<Account>();
	ArrayAdapter<Account> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_accounts);
		
		acListView = (ListView) findViewById(R.id.acListView);
		acListView.setOnItemClickListener(onItemListener);
		adapter = new ArrayAdapter<Account>(ShowAllAccountsActivity.this, android.R.layout.simple_list_item_1, accounts);
		acListView.setAdapter(adapter);
		
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(bt1Listener);
		
		updateListView();
	}

	private void updateListView() {
		String resourceURL = "http://10.0.2.2:4000/account";
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		httpClient.get(resourceURL, new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {
				
				accounts.clear();
				
				for (int i = 0; i < jsonArray.length(); i++) {
					try {
						JSONObject obj = jsonArray.getJSONObject(i);
						Account a = new Account(obj.getString("clientName"), obj.getLong("number"), obj.getDouble("balance"));
						accounts.add(a);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				adapter.notifyDataSetChanged();
			}
		});
	}
	
	private OnItemClickListener onItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Account ac = accounts.get(position);
			Toast.makeText(ShowAllAccountsActivity.this, "Its balance is " + ac.getBalance(), Toast.LENGTH_SHORT).show();
		}
	};

	private OnClickListener bt1Listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateListView();
		}
	};
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_all_accounts, menu);
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
