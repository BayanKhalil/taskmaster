package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() { // this is probably the correct place for ALL rendered info

        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = preferences.getString("userName","Go to Settings to set your username");
        TextView title  = findViewById(R.id.homePageTitle);
        title.setText(userName+"' Tasks");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button task2 = findViewById(R.id.button2);
        task2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = task2.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks",title);
                startActivity(detailsPage);

            }
        });



        Button task1 = findViewById(R.id.button1);
        task1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = task1.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks",title);
                startActivity(detailsPage);

            }
        });

        Button task3=findViewById(R.id.detailButton);
        task3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = task3.getText().toString();
                Intent detailsPage = new Intent(MainActivity.this, TaskDetails.class);
                detailsPage.putExtra("titleOfTasks",title);
                startActivity(detailsPage);

            }
        });

        Button settingsButton=findViewById(R.id.settingButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingPage=new Intent(MainActivity.this,settings.class);
                startActivity(settingPage);
            }
        });
    }




}