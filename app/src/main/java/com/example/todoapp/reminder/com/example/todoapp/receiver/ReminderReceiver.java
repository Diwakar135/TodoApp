package com.example.todoapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.todoapp.Utils.NotificationUtils;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String taskTitle = intent.getStringExtra("task_title");
        NotificationUtils.showNotification(context,
                "Task Reminder",
                taskTitle != null ? taskTitle : "You have a task to do!");
    }

}
