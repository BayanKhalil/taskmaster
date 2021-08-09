package com.example.taskmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("titleOfTasks"));
//        String title = intent.getStringExtra("titleOfTasks");

        TextView itemNameView = TaskDetails.this.findViewById(R.id.titleOfTask);
        itemNameView.setText(intent.getExtras().getString("titleOfTasks"));

    }
}