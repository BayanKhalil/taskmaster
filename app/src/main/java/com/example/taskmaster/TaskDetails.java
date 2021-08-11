package com.example.taskmaster;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
        System.out.println(intent.getExtras().getString("titleOfTasks"));


        TextView itemNameView = TaskDetails.this.findViewById(R.id.titleOfTask);
        itemNameView.setText(intent.getExtras().getString("titleOfTasks"));


        String title = getIntent().getStringExtra(MainActivity.title);
        TextView titleId = findViewById(R.id.titleOfTaskFromList);
        titleId.setText(title);

        String body = getIntent().getStringExtra(MainActivity.body);
        TextView bodyId = findViewById(R.id.bodyFromList);
        bodyId.setText(body);

        String state = getIntent().getStringExtra(MainActivity.state);
        TextView stateId = findViewById(R.id.stateFromList);
        stateId.setText(state);



    }
}