package com.example.mobbank_client.entity;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class LogTransaction {
	
	Context context;
	
	public LogTransaction(Context context) {
		this.context = context;
	}
	
	public void register(long number, String type, String desc) {
		String resourceURL = "http://10.0.2.2:4000/transaction";
		AsyncHttpClient httpClient = new AsyncHttpClient();
		
		//create Json object
		JSONObject params = new JSONObject();
		try {
			params.put("accountNumber", number);
			params.put("type", type);
			params.put("description", desc);
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
		
		httpClient.post(context, resourceURL, entity, "application/json", new JsonHttpResponseHandler() {
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
			};
		});
		
	}

}
