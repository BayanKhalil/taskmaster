package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;


import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;


public class TaskDetails extends AppCompatActivity {

    private static final String TAG = "taskDetails";
    private List<Task> taskList;
    private TaskAdapter adapter;
    private TaskDao taskDao;
    private AppDatabase database;
    private String fileURL;

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

        String team = getIntent().getStringExtra(MainActivity.teamId);
        TextView teamId = findViewById(R.id.teamFromList);
        teamId.setText(team);

//        TextView fileLinkDetail = findViewById(R.id.fileLinkDetail);
//        fileLinkDetail.setOnClickListener(view -> {
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(fileURL));
//            startActivity(i);
//        });


        String fileName = intent.getStringExtra(MainActivity.uploadedFile);
        @SuppressLint("CutPasteId") ImageView image = findViewById(R.id.taskImageDetail);
        getFileFromS3Storage(fileName);
        @SuppressLint("CutPasteId") TextView link = findViewById(R.id.fileLinkDetail);

        Picasso.get().load("https://taskstorage203032-dev.s3.amazonaws.com/public/"
                        + fileName).into(image);

        String linkToFile = "https://taskstorage203032-dev.s3.amazonaws.com/public/" + fileName;
        link.setText(linkToFile);
        link.setVisibility(View.VISIBLE);

//        database = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "TASK_LIST").allowMainThreadQueries().build();
//        taskDao = database.taskDao();
//
//        RecyclerView taskRecyclerView = findViewById(R.id.tasksList);
//        taskList = new ArrayList<>();
//        taskList = MainActivity.amplifyData();


    }

    private void getFileFromS3Storage(String key) {
        Amplify.Storage.downloadFile(
                key,
                new File(getApplicationContext().getFilesDir() + key),
                result -> {
                    Log.i(TAG, "Successfully downloaded: " + result.getFile().getAbsoluteFile());
                },
                error -> Log.e(TAG, "Download Failure", error)
        );
    }
}
