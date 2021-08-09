package com.example.taskmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);// getter
        SharedPreferences.Editor preferenceEditor = preferences.edit();



        findViewById(R.id.saveNameButton).setOnClickListener((view)->{

            EditText userName=findViewById(R.id.userName);
            preferenceEditor.putString("userName",userName.getText().toString());
            preferenceEditor.apply();

            Toast toast = Toast.makeText(this, "You saved your username", Toast.LENGTH_LONG);

            toast.show();
        });

//        findViewById(R.id.goHome).setOnClickListener((view) -> {
//                    Intent intent = new Intent(settings.this, MainActivity.class);
//                    settings.this.startActivity(intent);
//                }
//        );

    }
}