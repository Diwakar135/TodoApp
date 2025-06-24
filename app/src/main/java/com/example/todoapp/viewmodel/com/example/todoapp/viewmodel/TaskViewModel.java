package com.example.todoapp.viewmodel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapp.R;
import com.example.todoapp.model.ItemTask;
import com.example.todoapp.repository.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<ItemTask>> allTasks;
    @SuppressLint("StaticFieldLeak")
    private Activity itemView;
    @SuppressLint("StaticFieldLeak")
    TextView textViewReminder = itemView.findViewById(R.id.textViewReminder);

    public TaskViewModel(@NonNull Application application, Activity itemView) {
        super(application);
        repository = new TaskRepository(application);
        this.itemView = itemView;
        allTasks = repository.getAllTasks();
    }

    // Expose LiveData to UI
    public LiveData<List<ItemTask>> getAllTasks() {
        return allTasks;
    }

    public void insert(ItemTask task) {
        repository.insert(task);
    }

    public void update(ItemTask task) {
        repository.update(task);
    }

    public void delete(ItemTask task) {
        repository.delete(task);
    }

    public void deleteAllTasks() {
        repository.deleteAll();
    }
}
