package vipul.com.todolistapp.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import vipul.com.todolistapp.R;

public class TaskDetailActivity extends AppCompatActivity
        implements View.OnClickListener {

    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";

    private EditText taskTitleEditText;
    private EditText taskDescriptionEditText;

    private Button saveButton;
    private Button resetButton;
    private Button deleteButton;

    // Called by add new task button
    public static Intent getIntent(Context context) {
        return new Intent(context, TaskDetailActivity.class);
    }

    // Called by edit task button
    public static Intent getIntent(Context context, String title, String description) {
        Intent intent = new Intent(context, TaskDetailActivity.class);

        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_DESCRIPTION, description);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        taskTitleEditText = findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = findViewById(R.id.taskDescriptionEditText);

        saveButton = findViewById(R.id.saveButton);
        resetButton = findViewById(R.id.resetButton);
        deleteButton = findViewById(R.id.deleteButton);

        saveButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        if (getIntent() != null
                && getIntent().hasExtra(KEY_TITLE)
                && getIntent().hasExtra(KEY_DESCRIPTION)) {
            String title = getIntent().getStringExtra(KEY_TITLE);
            String description = getIntent().getStringExtra(KEY_DESCRIPTION);

            taskTitleEditText.setText(title);
            taskDescriptionEditText.setText(description);
            deleteButton.setVisibility(View.VISIBLE);
        } else {
            deleteButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.saveButton:
                saveContents();
                break;
            case R.id.resetButton:
                resetContents();
                break;
            case R.id.deleteButton:
                deleteTask();
                break;
        }
    }

    private void saveContents() {
        String title = taskTitleEditText.getText().toString();
        String desc = taskDescriptionEditText.getText().toString();

        if (TextUtils.isEmpty(title)) {
            taskTitleEditText.setError(getString(R.string.title_error_message));
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            taskDescriptionEditText.setError(getString(R.string.description_error_message));
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_DESCRIPTION, desc);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void resetContents() {
        taskTitleEditText.setText("");
        taskDescriptionEditText.setText("");

        taskTitleEditText.requestFocus();
    }

    private void deleteTask() {
        if (getIntent() != null) {
            Intent intent = new Intent();
            intent.putExtra(KEY_TITLE, getIntent().getStringExtra(KEY_TITLE));
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
