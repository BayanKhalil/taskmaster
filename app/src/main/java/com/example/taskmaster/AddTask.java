package com.example.taskmaster;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_task);
    }

            public void submitMessage(View v) {
                EditText editText =findViewById(R.id.editTextTextPersonName);
                String message=editText.getText().toString();
                Toast.makeText(this,message,Toast.LENGTH_LONG).show();
            }





    }


