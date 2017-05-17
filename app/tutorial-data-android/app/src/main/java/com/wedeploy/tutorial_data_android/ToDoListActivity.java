package com.wedeploy.tutorial_data_android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.wedeploy.android.WeDeploy;
import com.wedeploy.tutorial_data_android.databinding.ToDoListActivityBinding;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ToDoListActivity extends AppCompatActivity {

	private ToDoAdapter adapter;
	private List<String> todos = new ArrayList<>();
	private WeDeploy weDeploy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.to_do_list_activity);
		adapter = new ToDoAdapter(todos);
		weDeploy = new WeDeploy.Builder().build();

		ToDoListActivityBinding binding =
			DataBindingUtil.setContentView(this, R.layout.to_do_list_activity);

		binding.todoList.setAdapter(adapter);
		binding.todoList.setLayoutManager(
			new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		binding.todoList.addItemDecoration(
			new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		binding.todoList.setItemAnimator(new DefaultItemAnimator());

		binding.goToHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		populateList();
	}

	private void populateList() {
		// Add the code of the tutorial below

		// -------
	}

	private void parseAndAddTodos(JSONArray array) throws JSONException {
		List<String> newTodos = new ArrayList<>(array.length());
		for (int i = 0; i < array.length(); i++) {
			newTodos.add(array.getJSONObject(i).optString("name", ""));
		}

		todos.clear();
		todos.addAll(newTodos);
		adapter.notifyDataSetChanged();
	}
}
