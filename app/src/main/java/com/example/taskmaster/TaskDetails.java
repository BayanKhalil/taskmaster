package com.example.taskmaster;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;

public class TaskDetails extends AppCompatActivity {


    private TaskDao taskDao;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        Intent intent = getIntent();
//        System.out.println(intent.getExtras().getString("titleOfTasks"));
//
//
//        TextView itemNameView = TaskDetails.this.findViewById(R.id.titleOfTask);
//        itemNameView.setText(intent.getExtras().getString("titleOfTasks"));
//
//
//        String title = getIntent().getStringExtra(MainActivity.title);
//        TextView titleId = findViewById(R.id.titleOfTaskFromList);
//        titleId.setText(title);
//
//        String body = getIntent().getStringExtra(MainActivity.body);
//        TextView bodyId = findViewById(R.id.bodyFromList);
//        bodyId.setText(body);
//
//        String state = getIntent().getStringExtra(MainActivity.state);
//        TextView stateId = findViewById(R.id.stateFromList);
//        stateId.setText(state);
        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "TASK_LIST").allowMainThreadQueries().build();
        taskDao = database.taskDao();
        Amplify.API.query(
                ModelQuery.get(com.amplifyframework.datastore.generated.model.Task.class, intent.getExtras().getString("taskId")),
                response -> {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            TextView titleId = findViewById(R.id.titleOfTaskFromList);
                            titleId.setText(response.getData().getTitle());

                            TextView bodyId = findViewById(R.id.bodyFromList);
                            bodyId.setText(response.getData().getBody());

                            TextView stateId = findViewById(R.id.stateFromList);
                            stateId.setText(response.getData().getState());


                        }
                    });

                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );




    }
}