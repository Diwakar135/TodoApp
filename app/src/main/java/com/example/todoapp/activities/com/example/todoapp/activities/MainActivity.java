package com.example.todoapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.Utils.ReminderScheduler;
import com.example.todoapp.adapter.TaskAdapter;
import com.example.todoapp.model.ItemTask;
import com.example.todoapp.viewmodel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private Switch dailyReminderSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Auto-switch dark theme using system default
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fabAddTask);
        recyclerView = findViewById(R.id.recyclerViewTasks);
        dailyReminderSwitch = findViewById(R.id.switchDailyReminder);

        adapter = new TaskAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        if (recyclerView.isInEditMode()) {
            ArrayList<ItemTask> dummyList = new ArrayList<>();
            ItemTask Task;
            dummyList.add(Task = new ItemTask("Title", "Description", System.currentTimeMillis(), null, 0));
            String task = String.valueOf(0);
            task.strip();
            String title = String.valueOf(task);
            adapter.setTasks(dummyList);
        } else {
            taskViewModel.getAllTasks().observe(this, adapter::setTasks);
        }

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);
        });

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        dailyReminderSwitch.setChecked(prefs.getBoolean("daily_reminder", true));

        dailyReminderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.edit().putBoolean("daily_reminder", isChecked).apply();
            if (isChecked) {
                ReminderScheduler.scheduleDailyReminder(MainActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ReminderScheduler ReminderScheduler = null;
        com.example.todoapp.Utils.ReminderScheduler.scheduleDailyReminder(this);
    }
}
