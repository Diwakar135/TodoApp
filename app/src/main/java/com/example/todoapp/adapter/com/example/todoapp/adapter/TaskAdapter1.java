package com.example.todoapp.adapter;

import androidx.annotation.NonNull;

import com.example.todoapp.adapter.TaskAdapter.TaskViewHolder;

public interface TaskAdapter1 {
    @NonNull
    default TaskViewHolder onCreateViewHolder() {
        TaskViewHolder o = null;
        return null;
    }
}
