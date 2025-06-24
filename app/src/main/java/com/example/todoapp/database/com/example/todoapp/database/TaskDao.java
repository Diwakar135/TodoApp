package com.example.todoapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.ItemTask;

import java.util.List;


@Dao
public interface TaskDao {

    @Insert
    void insertTask(ItemTask task);

    @Update
    void updateTask(ItemTask task);

    @Delete
    void deleteTask(ItemTask task);

    @Query("DELETE FROM tasks")
    void deleteAllTasks();

    @Query("SELECT * FROM tasks ORDER BY timestamp DESC")
    LiveData<List<ItemTask>> getAllTasks();
}
