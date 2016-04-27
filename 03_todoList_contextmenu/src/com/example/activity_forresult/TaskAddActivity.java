package com.example.activity_forresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class TaskAddActivity extends Activity {
	EditText taskNameET, taskDescET;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_add);
		
		Button btAddTask = (Button) findViewById(R.id.addTaskButton);
		btAddTask.setOnClickListener(btAddTaskListener);
		
		taskNameET = (EditText) findViewById(R.id.taskNameEditText);
		taskDescET = (EditText) findViewById(R.id.taskDescEditText);
	}
	
	private OnClickListener btAddTaskListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String tname = taskNameET.getText().toString();
			String tdesc = taskDescET.getText().toString();
			Task task = new Task(tname, tdesc);
			
			Intent i = new Intent();
			i.putExtra("taskAdded", task);
			setResult(RESULT_OK, i);
			TaskAddActivity.this.finish();
		}
	};
}
