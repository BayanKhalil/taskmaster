package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class settings extends AppCompatActivity {

    //    private final List<String> teams = new ArrayList<>();
    private final List<String> spinnerData = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);// getter
        SharedPreferences.Editor preferenceEditor = preferences.edit();

        Spinner teamSpinner = findViewById(R.id.TeamSpinner);

        Amplify.API.query(ModelQuery.list(Team.class),
                response -> {
                    Team team1 = Team.builder().name("Team1").build();
                    Team team2 = Team.builder().name("Team2").build();
                    Team team3 = Team.builder().name("Team3").build();

                        spinnerData.add(team1.getName());
                        spinnerData.add(team2.getName());
                        spinnerData.add(team3.getName());


                    //create an adapter to describe how the items are displayed, adapters are used in several places in android.
                    arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerData);
                    runOnUiThread(() -> {
                        //set the spinners adapter to the previously created one.
                        teamSpinner.setAdapter(arrayAdapter);
                    });

                },
                error -> Log.e("TEAM_ERROR", "onCreate: ", error));

        findViewById(R.id.saveNameButton).setOnClickListener(view -> {
            EditText username = findViewById(R.id.userName);
            String userName = username.getText().toString();
            @SuppressLint("CutPasteId") String taskTeam = ((Spinner) findViewById(R.id.TeamSpinner)).getSelectedItem().toString();




             Amplify.API.query(ModelQuery.list(Team.class, Team.NAME.eq(taskTeam)),
                    response -> {
                        List<Team> teamList1 = new ArrayList<>();
                        Team team1 = Team.builder().name("Team1").build();
                        Team team2 = Team.builder().name("Team2").build();
                        Team team3 = Team.builder().name("Team3").build();
                        teamList1.add(team1);
                        teamList1.add(team2);
                        teamList1.add(team3);

                        for (Team team: response.getData()) {
                            teamList1.add(team);
                        }
                        preferenceEditor.putString("userName", userName);
                            preferenceEditor.putString("teamId", teamList1.get(0).getId());
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+teamList1.get(0).getId());
                            preferenceEditor.putString("teamId", teamList1.get(1).getId());
                            preferenceEditor.putString("teamId", teamList1.get(2).getId());

                        preferenceEditor.putString("teamName", taskTeam);
                        preferenceEditor.apply();
                    },
                    err -> Log.e("ERROR", "onClick: ",err ));


            Toast.makeText(getApplicationContext(), userName +taskTeam+ "saved", Toast.LENGTH_SHORT).show();


        });



    }
}