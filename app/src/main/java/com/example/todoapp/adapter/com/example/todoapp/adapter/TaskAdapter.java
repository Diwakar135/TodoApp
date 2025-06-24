// TaskAdapter.java
package com.example.todoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.todoapp.R;
import com.example.todoapp.activities.EditTaskActivity;
import com.example.todoapp.model.ItemTask;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<ItemTask> tasks = new ArrayList<>();
    private final Context context;

    public TaskAdapter(Context context) {
        this.context = context;
    }

    public void setTasks(List<ItemTask> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        ItemTask task = tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.description.setText(task.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditTaskActivity.class);
            intent.putExtra("task_id", task.getId());
            intent.putExtra("task_title", task.getTitle());
            intent.putExtra("task_description", task.getDescription());
            intent.putExtra("task_image_path", task.getImagePath());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            image = itemView.findViewById(R.id.imageViewTask);
        }
    }
}
