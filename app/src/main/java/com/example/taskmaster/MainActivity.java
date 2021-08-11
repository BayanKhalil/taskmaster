package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String title = "title";
    public static final String body = "body";
    public static final String state = "state";

    private List<Task> taskList;
    private TaskAdapter adapter;



    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() { // this is probably the correct place for ALL rendered info

        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName","Go to Settings to set your username");
        TextView title  = findViewById(R.id.homePageTitle);
        title.setText(userName+"'s Tasks");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView taskRecyclerView = findViewById(R.id.tasksList);
        taskList = new ArrayList<>();
        taskList.add(new Task("Task 1", "study", "new"));
        taskList.add(new Task("Task 2", "eat", "assigned"));
        taskList.add(new Task("Task 3", "play", "in progress"));
        taskList.add(new Task("Task 4", "code", "complete"));
        taskList.add(new Task("Task 5", "watch tv", "new"));
        taskList.add(new Task("Task 6", "clean my room", "assigned"));

        adapter = new TaskAdapter(taskList, new TaskAdapter.OnTaskItemClickListener() {

            @Override
            public void onItemClicked(int position) {
                Intent goToDetailsIntent = new Intent(getApplicationContext(), TaskDetails.class);
                goToDetailsIntent.putExtra(title, taskList.get(position).getTitle());
                goToDetailsIntent.putExtra(body, taskList.get(position).getBody());
                goToDetailsIntent.putExtra(state, taskList.get(position).getState());
                startActivity(goToDetailsIntent);
            }

            @Override
            public void onDeleteItem(int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);


      taskRecyclerView.setLayoutManager(linearLayoutManager);
        taskRecyclerView.setAdapter(adapter);

        Button settings = MainActivity.this.findViewById(R.id.settingButton);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, settings.class);
                startActivity(settingsIntent);
            }
        });




            Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent addButton = new Intent(MainActivity.this, AddTask.class);
                MainActivity.this.startActivity(addButton);
            }
            });

            Button allTasksButton = findViewById(R.id.allTaskButton);
        allTasksButton.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent allTasksButton = new Intent(MainActivity.this, AllTasks.class);
                MainActivity.this.startActivity(allTasksButton);
            }
            });

            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            Button task1 = findViewById(R.id.button1);
        task1.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                String title = task1.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks", title);
                startActivity(detailsPage);

            }
            });


            Button task2 = findViewById(R.id.button2);
        task2.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                String title = task2.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks", title);
                startActivity(detailsPage);

            }
            });


            Button task3 = findViewById(R.id.detailButton);
        task3.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                String title = task3.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks", title);
                startActivity(detailsPage);

            }
            });

            Button settingsButton = findViewById(R.id.settingButton);
        settingsButton.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent settingPage = new Intent(MainActivity.this, settings.class);
                startActivity(settingPage);
            }
            });




        }


    }





