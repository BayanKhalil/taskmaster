package com.example.taskmaster;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.taskmaster.databinding.ActivityAddTaskBinding;

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


