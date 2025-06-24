package com.example.todoapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.database.AppDatabase;
import com.example.todoapp.database.TaskDao;
import com.example.todoapp.model.ItemTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<ItemTask>> allTasks;
    private ExecutorService executorService;

    public TaskRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(ItemTask task) {
        executorService.execute(() -> taskDao.insertTask(task));
    }

    public void update(ItemTask task) {
        executorService.execute(() -> taskDao.updateTask(task));
    }

    public void delete(ItemTask task) {
        executorService.execute(() -> taskDao.deleteTask(task));
    }

    public void deleteAll() {
        executorService.execute(() -> taskDao.deleteAllTasks());
    }

    public LiveData<List<ItemTask>> getAllTasks() {
        return allTasks;
    }
}
