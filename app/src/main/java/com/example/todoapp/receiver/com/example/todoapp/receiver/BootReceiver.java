package com.example.todoapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.todoapp.Utils.ReminderScheduler;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Re-schedule the daily reminder after reboot
        ReminderScheduler.scheduleDailyReminder(context);
    }
}
