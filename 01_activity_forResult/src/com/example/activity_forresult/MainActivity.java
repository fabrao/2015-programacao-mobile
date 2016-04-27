package com.example.activity_forresult;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private final int TASK_ADD_CODE = 23;
	ListView taskListView;
	ArrayList<Task> tasks = new ArrayList<Task>();
	ArrayAdapter<Task> tasksAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button bt_add = (Button) findViewById(R.id.addButton);
		bt_add.setOnClickListener(addBtListener);
		
		tasksAdapter = new ArrayAdapter<Task>(MainActivity.this, android.R.layout.simple_list_item_1, tasks);
		taskListView = (ListView) findViewById(R.id.taskListView);
		taskListView.setAdapter(tasksAdapter);		
	}
	
	private OnClickListener addBtListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, TaskAddActivity.class);
			startActivityForResult(i, TASK_ADD_CODE);
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == TASK_ADD_CODE && resultCode == RESULT_OK) {
			Task tTask = (Task) data.getSerializableExtra("taskAdded");
			//adiciono ao arraylist
			tasks.add(tTask);
			//notifico o adaptador para atualizar a interface
			tasksAdapter.notifyDataSetChanged();
		}
	}
}
