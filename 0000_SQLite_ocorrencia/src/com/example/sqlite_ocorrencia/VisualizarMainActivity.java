package com.example.sqlite_ocorrencia;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class VisualizarMainActivity extends Activity {

	private SQLiteDatabase database;
	private ActionMode currentActionMode;
    ListView listView;
    DBHelper helper;
    
    int currentPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualizar_main);
		

		listView = (ListView) findViewById(R.id.listView1);
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();
        
        Cursor cursor = database.rawQuery("SELECT * FROM ocorrencia", null);
	    cursor.moveToFirst();
        int i=0;
        List <String> nomes = new ArrayList<String>();
        
        while(! cursor.isAfterLast()) {	        
	        nomes.add(i, cursor.getString(1));
	        cursor.moveToNext();
	        i++;
        }
        cursor.close();
        
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_expandable_list_item_2, android.R.id.text1, nomes);  
        listView.setAdapter(ad); 
	}
	
	/**
	 * classe anonima que trata um clique longo em algum item do ListView
	 */
	private OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view,
				int position, long id) {
			if (currentActionMode != null)
				return false;

			currentPosition = position;	//armazena o item atualmente selecionado
			currentActionMode = startActionMode(modeCallBack);
			view.setSelected(true);
			return false;
		}
	};
	
	
	/**
	 * Classe anonima que implementa a interface ActionMode.Callback
	 * usada para criar um context menu (usando o actionBar)
	 */
	private ActionMode.Callback modeCallBack = new ActionMode.Callback() {
		
		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			mode.setTitle("Actions");
			mode.getMenuInflater().inflate(R.menu.context_menu, menu);
			return true;
		}
		
		// chamado quando o usuario seleciona um item do contextual menu
		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
			switch(item.getItemId()) {
			case R.id.showItem:
				Builder builder = new Builder(VisualizarMainActivity.this); 
				//Task tTask = tasks.get(currentPosition); 
				//String tMsg = "Name: " + tTask.getName() + "\n" + "Description: " + tTask.getDescription();
				builder.setTitle("Task details");
				//builder.setMessage(tMsg);
				builder.setPositiveButton("OK", null);
				builder.create().show();
				mode.finish();	//encerra o action mode
				return true;
				
			case R.id.deleteItem:
				//tasks.remove(currentPosition); 
				//tasksAdapter.notifyDataSetChanged();
				mode.finish();	//encerra o action mode
				return true;
			}	
			return false;
		}

		//chamado to vez que o action mode eh apresentado
		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		//chamado quando o usuario sai do action mode
		@Override
		public void onDestroyActionMode(ActionMode mode) {
			currentActionMode = null; //limpa o current mode atual
		}
	};
	
	
}
