package com.example.taskmaster;

import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{


    private final List<Task> tasks;
    private OnTaskItemClickListener listener;

    public interface OnTaskItemClickListener {
        void onItemClicked(int position);
        void onDeleteItem(int position);
    }

    public TaskAdapter(List<Task> tasks, OnTaskItemClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task item = tasks.get(position);

        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());
        holder.state.setText(item.getState());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView body;
        private TextView state;

        ViewHolder(@NonNull View itemView, OnTaskItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.task_title);
            body = itemView.findViewById(R.id.task_body);
            state = itemView.findViewById(R.id.task_state);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.onItemClicked(getAdapterPosition());
                }
            });

        }
    }


}
