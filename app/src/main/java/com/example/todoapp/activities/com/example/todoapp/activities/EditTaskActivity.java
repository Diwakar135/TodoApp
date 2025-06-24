// EditTaskActivity.java
package com.example.todoapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.R;
import com.example.todoapp.model.ItemTask;
import com.example.todoapp.viewmodel.TaskViewModel;

public class EditTaskActivity extends AppCompatActivity {

    private EditText editTitle, editDescription;
    private ImageView imageView;
    private Button btnSave;
    private TaskViewModel taskViewModel;

    private int taskId;
    private String imagePath;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTitle = findViewById(R.id.editTextTitle);
        editDescription = findViewById(R.id.editTextDescription);
        int ImageViewEdit = 0;
        imageView = findViewById(ImageViewEdit);
        btnSave = findViewById(R.id.buttonSave);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra("task_id")) {
            taskId = intent.getIntExtra("task_id", -1);
            editTitle.setText(intent.getStringExtra("task_title"));
            editDescription.setText(intent.getStringExtra("task_description"));
            imagePath = intent.getStringExtra("task_image_path");
            // Load image with Glide or similar if needed
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString().trim();
                String description = editDescription.getText().toString().trim();

                if (title.isEmpty() || description.isEmpty()) {
                    Toast.makeText(EditTaskActivity.this, "Please enter title and description", Toast.LENGTH_SHORT).show();
                    return;
                }

                ItemTask updatedTask = new ItemTask(title, description, System.currentTimeMillis(), imagePath, 0);
                updatedTask.setId(taskId);
                taskViewModel.update(updatedTask);

                Toast.makeText(EditTaskActivity.this, "Task updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private class ImageViewEdit {
    }
}
