package com.example.taskmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addButton = findViewById(R.id.button2);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addButton=new Intent(MainActivity.this,AddTask.class);
                MainActivity.this.startActivity(addButton);
            }
        });

        Button allTasksButton = findViewById(R.id.button);
        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allTasksButton=new Intent(MainActivity.this,AllTasks.class);
                MainActivity.this.startActivity(allTasksButton);
            }
        });
    }




}