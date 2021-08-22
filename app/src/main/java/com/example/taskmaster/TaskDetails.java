package com.example.taskmaster;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDetails extends AppCompatActivity {

    private List<Task> taskList;
    private TaskAdapter adapter;
    private TaskDao taskDao;
    private AppDatabase database;
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

//        String team = getIntent().getStringExtra(MainActivity.team);
//        TextView teamId = findViewById(R.id.teamFromList);
//        teamId.setText(team);


//        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "TASK_LIST").allowMainThreadQueries().build();
//        taskDao = database.taskDao();

//        RecyclerView taskRecyclerView = findViewById(R.id.tasksList);
//       taskList = new ArrayList<>();
//        taskList = MainActivity.amplifyData();







    }
}