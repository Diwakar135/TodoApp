package com.example.todoapp.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class ItemTask {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private long timestamp;

    @Nullable
    private String imagePath;

    private long reminderTime;

    // ✅ Required empty constructor for Room
    public ItemTask() {}

    // ✅ Constructor where parameter names match field names
    public ItemTask(String title, String description, long timestamp, @Nullable String imagePath, long reminderTime) {
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
        this.imagePath = imagePath;
        this.reminderTime = reminderTime;
    }

    // ✅ Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(@Nullable String imagePath) {
        this.imagePath = imagePath;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }
}
