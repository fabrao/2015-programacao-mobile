package com.example.activity_forresult;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final int TASK_ADD_CODE = 23;
	ListView taskListView;
	ArrayList<Task> tasks = new ArrayList<Task>();
	ArrayAdapter<Task> tasksAdapter;
	int currentPosition;
	// Tracks current contextual action mode
	private ActionMode currentActionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_add = (Button) findViewById(R.id.addButton);
		bt_add.setOnClickListener(addBtListener);

		tasksAdapter = new ArrayAdapter<Task>(MainActivity.this,
				android.R.layout.simple_list_item_1, tasks);
		taskListView = (ListView) findViewById(R.id.taskListView);
		taskListView.setAdapter(tasksAdapter);

		//
		taskListView.setOnItemLongClickListener(onItemLongClickListener);
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
				Builder builder = new Builder(MainActivity.this); 
				Task tTask = tasks.get(currentPosition); 
				String tMsg = "Name: " + tTask.getName() + "\n" + "Description: " + tTask.getDescription();
				builder.setTitle("Task details");
				builder.setMessage(tMsg);
				builder.setPositiveButton("OK", null);
				builder.create().show();
				mode.finish();	//encerra o action mode
				return true;
				
			case R.id.deleteItem:
				tasks.remove(currentPosition); 
				tasksAdapter.notifyDataSetChanged();
				mode.finish();	//encerra o action mode
				return true;
				
			case R.id.toTopItem:
				tTask = tasks.remove(currentPosition); 
				tasks.add(0, tTask);
				tasksAdapter.notifyDataSetChanged();
				mode.finish();	//encerra o action mode
				return true;
				
			case R.id.toEndItem:
				tTask = tasks.remove(currentPosition);
				tasks.add(tTask);
				tasksAdapter.notifyDataSetChanged();
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

	private OnClickListener addBtListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, TaskAddActivity.class);
			startActivityForResult(i, TASK_ADD_CODE);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TASK_ADD_CODE && resultCode == RESULT_OK) {
			Task tTask = (Task) data.getSerializableExtra("taskAdded");
			// adiciono ao arraylist
			tasks.add(tTask);
			// notifico o adaptador para atualizar a interface
			tasksAdapter.notifyDataSetChanged();
		}
	}
}
