package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.datastore.generated.model.Task;

import com.amplifyframework.core.Amplify;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String title = "title";
    public static final String body = "body";
    public static final String state = "state";
    public static final String teamId = "teamId";
    public static final String uploadedFile = "uploadedFile";



    private List<Task> taskList;


    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() { // this is probably the correct place for ALL rendered info

        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName", "Go to Settings to set your username");
        TextView title = findViewById(R.id.homePageTitle);
        title.setText(userName + "'s Tasks");

        ((TextView) findViewById(R.id.homePageTeam)).setText(preferences.getString("teamName", "All Task"));





    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amplifyData();


        RecyclerView taskRecyclerView = findViewById(R.id.tasksList);
//        taskList = new ArrayList<>();
        taskList = amplifyData();
        TaskAdapter adapter = new TaskAdapter(taskList, position -> {
            Intent goToDetailsIntent = new Intent(getApplicationContext(), TaskDetails.class);
            goToDetailsIntent.putExtra(title, taskList.get(position).getTitle());
            goToDetailsIntent.putExtra(body, taskList.get(position).getBody());
            goToDetailsIntent.putExtra(state, taskList.get(position).getState());
            goToDetailsIntent.putExtra(teamId, taskList.get(position).getTeamId());
            goToDetailsIntent.putExtra(uploadedFile, taskList.get(position).getUploadedFile());
            startActivity(goToDetailsIntent);
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false);

        taskRecyclerView.setLayoutManager(linearLayoutManager);
        taskRecyclerView.setAdapter(adapter);



        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> {
            Intent addButton1 = new Intent(MainActivity.this, AddTask.class);
            MainActivity.this.startActivity(addButton1);
        });

        Button allTasksButton = findViewById(R.id.allTaskButton);
        allTasksButton.setOnClickListener(v -> {
            Intent allTasksButton1 = new Intent(MainActivity.this, AllTasks.class);
            MainActivity.this.startActivity(allTasksButton1);
        });


        Button settingsButton = findViewById(R.id.settingButton);
        settingsButton.setOnClickListener(v -> {
            Intent settingPage = new Intent(MainActivity.this, settings.class);
            startActivity(settingPage);
        });

        Button  Logout = (Button) findViewById(R.id.buttonLogout);
//        Intent in = getIntent();
//        String string = in.getStringExtra("message");
        Logout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirmation PopUp!").
                    setMessage("You sure, that you want to logout?");
            builder.setPositiveButton("Yes",
                    (dialog, id) -> {
                        Intent i = new Intent(getApplicationContext(),
                                SignIn.class);
                        startActivity(i);
                    });
            builder.setNegativeButton("No",
                    (dialog, id) -> dialog.cancel());
            AlertDialog alert11 = builder.create();
            alert11.show();
        });
    }


//       >>>>>>>>>>>>>>>> amplify <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void  saveDataToAmplify(String title,String body ,String state,String teamId,String uploadedFile){
        com.amplifyframework.datastore.generated.model.Task item = com.amplifyframework.datastore.generated.model.Task.builder().title(title).state(state).teamId(teamId).body(body).uploadedFile(uploadedFile).build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().toString()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );

    }

    public synchronized static List<Task> amplifyData(){
        System.out.println("In get data");
        List<Task> list = new ArrayList<>();
        Amplify.DataStore.query(Task.class,tasks ->{
                    while (tasks.hasNext()) {
                        Task task = tasks.next();
                        list.add(task);
                        Log.i("Tutorial", "==== Task ====");
                        Log.i("Tutorial", "TITLE : " + task.getTitle());
                        Log.i("Tutorial", "BODY : " + task.getBody());
                        Log.i("Tutorial", "STATE : " + task.getState());
                        Log.i("Tutorial", "teamID : " + task.getTeamId());
                        Log.i("Tutorial", "file : " + task.getUploadedFile());
                        Log.i("Tutorial", "==== Task End ====");
                    }
                }, failure -> Log.e("Tutorial", "Could not query DataStore", failure)

        );

        return list;
    }

}