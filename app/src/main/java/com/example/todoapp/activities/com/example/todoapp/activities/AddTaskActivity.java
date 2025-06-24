package com.example.todoapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.R;
import com.example.todoapp.model.ItemTask;
import com.example.todoapp.viewmodel.TaskViewModel;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription;
    private Button btnSave;
    private TaskViewModel taskViewModel;

    private String imagePath = null; // Optional: handle image if needed
    private long reminderTime = 0;   // Optional: allow reminder feature

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTitle = findViewById(R.id.editTextTitle);
        editDescription = findViewById(R.id.editTextDescription);
        btnSave = findViewById(R.id.buttonSave);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString().trim();
                String description = editDescription.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(AddTaskActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ✅ Create task with all required fields
                ItemTask task = new ItemTask(title, description, System.currentTimeMillis(), imagePath, reminderTime);

                // ✅ Save to database
                taskViewModel.insert(task);

                // ✅ Return result
                Intent intent = new Intent();
                intent.putExtra("task_title", task.getTitle());
                intent.putExtra("task_description", task.getDescription());
                intent.putExtra("task_image_path", task.getImagePath());

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}
