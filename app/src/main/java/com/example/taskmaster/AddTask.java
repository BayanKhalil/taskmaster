package com.example.taskmaster;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;

public class AddTask extends AppCompatActivity {

    private String spinnerState;
    private static final String TASK_LIST ="task-list" ;
    private AppDatabase database;
    private TaskDao taskDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);





            Spinner spinner = findViewById(R.id.spinner);
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.stateArray, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                    spinnerState = (String) adapterView.getItemAtPosition(position);
                    System.out.println(spinnerState);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });



        Button InAddTAsk=findViewById(R.id.button3);
        InAddTAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputTitle = findViewById(R.id.editText);
                EditText inputDescription = findViewById(R.id.editTextDescription);


                String title = inputTitle.getText().toString();
                String description = inputDescription.getText().toString();
                String taskStatus = spinnerState;



              MainActivity.saveDataToAmplify(title, description, taskStatus);
                Toast.makeText(getApplicationContext(), "Task Added", Toast.LENGTH_SHORT).show();



            }

        });

    }


    }


