package vipul.com.todolistapp.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import vipul.com.todolistapp.R;
import vipul.com.todolistapp.detail.TaskDetailActivity;
import vipul.com.todolistapp.model.MyTask;

public class TasksListActivity extends AppCompatActivity implements TaskListAdapter.OnTaskSelectedListener {

    private List<MyTask> tasks; // Mutable
    private RecyclerView tasksRecyclerView;
    private TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);

        tasks = new ArrayList<>();

        tasks.add(new MyTask("Task 1", "Task 1 Description"));
        tasks.add(new MyTask("Task 2", "Task 2 Description"));

        adapter = new TaskListAdapter(tasks);
        adapter.setListener(this);
        tasksRecyclerView.setAdapter(adapter);
    }

    // Loading a menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivityForResult(TaskDetailActivity.getIntent(this), 111);
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onTaskSelected(MyTask task) {
        startActivityForResult(TaskDetailActivity.getIntent(this,
                task.getTitle(),
                task.getDescription()), 222);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 111) {
            String title = data.getStringExtra(TaskDetailActivity.KEY_TITLE);
            String description = data.getStringExtra(TaskDetailActivity.KEY_DESCRIPTION);
            MyTask task = new MyTask(title, description);
            tasks.add(task);
            adapter.setTasks(tasks);
        } else if (requestCode == 222) {
            if (resultCode == RESULT_OK) {
                String title = data.getStringExtra(TaskDetailActivity.KEY_TITLE);
                if (title != null) {
                    int position = tasks.indexOf(new MyTask(title, null));
                    if (position != -1) {
                        tasks.remove(position);
                        adapter.setTasks(tasks);
                    }
                }
            }
        }
    }
}
