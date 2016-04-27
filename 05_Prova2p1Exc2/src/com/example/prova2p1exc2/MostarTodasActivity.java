package com.example.prova2p1exc2;

import java.util.ArrayList;








import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MostarTodasActivity extends ActionBarActivity {
	Button updateBT;
	LinearLayout transLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostar_todas);
		
		updateBT = (Button) findViewById(R.id.button2);
		updateBT.setOnClickListener(bt2OnClickListener);
		
		Button btVoltar = (Button) findViewById(R.id.button1);
		btVoltar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MostarTodasActivity.this.finish();
			}
		});
		
		transLayout = (LinearLayout) findViewById(R.id.CatLinearLayout);
		updateTransactions();
	}
	
	private void updateTransactions() {
		
		transLayout.removeAllViews();
		
		String resourceURL = "http://10.0.2.2:4000/categoria";
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
						
						TextView tv = new TextView(MostarTodasActivity.this);
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
	
}
