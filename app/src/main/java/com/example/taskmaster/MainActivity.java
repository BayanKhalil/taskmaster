package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
 import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public static final String title = "title";
    public static final String body = "body";
    public static final String state = "state";



    private List<Task> taskList;
    private TaskAdapter adapter;

    private TaskDao taskDao;
    private AppDatabase database;


    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() { // this is probably the correct place for ALL rendered info

        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName", "Go to Settings to set your username");
        TextView title = findViewById(R.id.homePageTitle);
        title.setText(userName + "'s Tasks");

        amplifyData();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }



            setContentView(R.layout.activity_main);

            RecyclerView taskRecyclerView = findViewById(R.id.tasksList);
            taskList = new ArrayList<>();
            taskList = amplifyData();
            adapter = new TaskAdapter(taskList, new TaskAdapter.OnTaskItemClickListener() {

                @Override
                public void onItemClicked(int position) {
                    Intent goToDetailsIntent = new Intent(getApplicationContext(), TaskDetails.class);
                    goToDetailsIntent.putExtra(title, taskList.get(position).getTitle());
                    goToDetailsIntent.putExtra(body, taskList.get(position).getBody());
                    goToDetailsIntent.putExtra(state, taskList.get(position).getState());
                    startActivity(goToDetailsIntent);
                }

            });
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                    this,
                    LinearLayoutManager.VERTICAL,
                    false);

            taskRecyclerView.setLayoutManager(linearLayoutManager);
            taskRecyclerView.setAdapter(adapter);



            Button addButton = findViewById(R.id.addButton);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent addButton = new Intent(MainActivity.this, AddTask.class);
                    MainActivity.this.startActivity(addButton);
                }
            });

            Button allTasksButton = findViewById(R.id.allTaskButton);
            allTasksButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent allTasksButton = new Intent(MainActivity.this, AllTasks.class);
                    MainActivity.this.startActivity(allTasksButton);
                }
            });


            Button settingsButton = findViewById(R.id.settingButton);
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent settingPage = new Intent(MainActivity.this, settings.class);
                    startActivity(settingPage);
                }
            });





    }

//    >>>>>>>>>>>>>>>> amplify <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public static void  saveDataToAmplify(String title,String body ,String state){
        com.amplifyframework.datastore.generated.model.Task item = com.amplifyframework.datastore.generated.model.Task.builder().title(title).body(body).state(state).build();

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
                        Log.i("Tutorial", "==== Task End ====");
                    }
                }, failure -> Log.e("Tutorial", "Could not query DataStore", failure)

        );

        return list;
    }

}