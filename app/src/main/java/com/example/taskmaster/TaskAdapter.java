package com.example.taskmaster;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class TaskAdapter  extends RecyclerView.Adapter<TaskAdapter.ViewHolder>{


//    private final List<Task> tasks;
    private final List<com.amplifyframework.datastore.generated.model.Task> amplifyTasks;
    private final OnTaskItemClickListener listener;

    public interface OnTaskItemClickListener {
        void onItemClicked(int position);

    }

    public TaskAdapter(List<com.amplifyframework.datastore.generated.model.Task> amplifyTasks, OnTaskItemClickListener listener) {
        this.amplifyTasks = amplifyTasks;
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
//        Task item = tasks.get(position);
        com.amplifyframework.datastore.generated.model.Task amplifyItem = amplifyTasks.get(position);

        holder.title.setText(amplifyItem.getTitle());
        holder.body.setText(amplifyItem.getBody());
        holder.state.setText(amplifyItem.getState());

    }

    @Override
    public int getItemCount() {
        return amplifyTasks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView body;
        private final TextView state;


        ViewHolder(@NonNull View itemView, OnTaskItemClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.task_title);
            body = itemView.findViewById(R.id.task_body);
            state = itemView.findViewById(R.id.task_state);


            itemView.setOnClickListener(v -> listener.onItemClicked(getAdapterPosition()));

        }
    }


}
