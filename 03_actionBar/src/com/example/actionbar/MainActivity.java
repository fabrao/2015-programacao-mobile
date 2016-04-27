package com.example.actionbar;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button filterButton = (Button) findViewById(R.id.filterButton);
		filterButton.setOnClickListener(filterBtListener);
	}
	
	/**
	 * classe anonima que implementa um listener para o botao "filter by"
	 */
	private OnClickListener filterBtListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			mostrarPopupMenu(v);			
		}
	};
	
	/**
	 * metodo que mostra um popup menu para o botao
	 * 
	 * @param v
	 */
	private void mostrarPopupMenu(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		
		popup.getMenuInflater().inflate(R.menu.popup_filter, popup.getMenu());
		
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch (item.getItemId()) {
				case R.id.item1:
					Toast.makeText(MainActivity.this, "Filter by keyword!!", Toast.LENGTH_SHORT).show();
					return true;

				case R.id.item2:
					Toast.makeText(MainActivity.this, "Filter by popularity!!", Toast.LENGTH_SHORT).show();
					return true;

				default:
					return false;
				}
			}
		});
		
		popup.show();
	}	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Toast.makeText(this, "selecionou settings", Toast.LENGTH_SHORT).show();
			return true;
		}
		else if(id == R.id.item1) {
			Toast.makeText(this, "selecionou item 1", Toast.LENGTH_SHORT).show();
			return true;			
		}
		else if(id == R.id.item2) {
			Toast.makeText(this, "selecionou item 2", Toast.LENGTH_SHORT).show();
			return true;			
		}
		
		return super.onOptionsItemSelected(item);
	}
}
