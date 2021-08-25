package com.example.taskmaster;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Team;
import com.amplifyframework.datastore.generated.model.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.Q)
public class AddTask extends AppCompatActivity {

    private static final int REQUEST_FOR_FILE = 999;
    private static final String TAG = "ADD TASK";
    private String spinnerState;
    private AppDatabase database;
    private TaskDao taskDao;

    private String uploadFileName;



    @RequiresApi(api = 29)
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

//      >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>3333333<<<<<<<<<<<<<<<<<<<<<<

        Spinner teamSpinner = findViewById(R.id.task_team_spinner);
        List<String> teamNameList = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();
        Amplify.API.query(ModelQuery.list(Team.class),
                response -> {

                    Team team1 = Team.builder().name("Team1").build();
                    Team team2 = Team.builder().name("Team2").build();
                    Team team3 = Team.builder().name("Team3").build();

//
                        teamNameList.add(team1.getName());
                        teamNameList.add(team2.getName());
                        teamNameList.add(team3.getName());
                        teamList.add(team1);
                        teamList.add(team2);
                        teamList.add(team3);

                    ArrayAdapter<String> teamSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, teamNameList);
                    runOnUiThread(() -> {
                        teamSpinner.setAdapter(teamSpinnerAdapter);
                    });

                },
                error -> {
                    Log.e("ERROR", "onCreate: ", error);
                });
//        <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<33333>>>>>>>>>>>>>>>>>>>>>>>>

        Button InAddTAsk=findViewById(R.id.button3);
        InAddTAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText inputTitle = findViewById(R.id.editText);
                EditText inputDescription = findViewById(R.id.editTextDescription);


                String title = inputTitle.getText().toString();
                String description = inputDescription.getText().toString();
                String taskStatus = spinnerState;
                String taskTeam = ((Spinner) findViewById(R.id.task_team_spinner)).getSelectedItem().toString();
                String teamId = "";
                String teamName = "";
                for (Team team: teamList) {
                    if (team.getName().equals(taskTeam)){
                        teamId = team.getId();
//                        teamName = team.getName();
                    }
                }

                String fileName = uploadFileName ;

                Task task = Task.builder().title(title).state(taskStatus).teamId(teamId).body(description).uploadedFile(fileName).build();


                MainActivity.saveDataToAmplify(title, description, taskStatus,teamId,fileName);

                Amplify.API.mutate(ModelMutation.create(task),
                        response -> {
                            Log.i("app", "task  " + response.getData().getId());
//                            taskDao.addTask(task);

                        },
                        error -> Log.e("add task", "Create failed", error)
                );

                Toast.makeText(getBaseContext(), "Task was added", Toast.LENGTH_SHORT).show();
            }
        });


        Button pickFileButton = findViewById(R.id.upload);
        pickFileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileFromDevice();
                Toast.makeText(AddTask.this, "", Toast.LENGTH_SHORT).show();
            }
        });



            }


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.37--S3------<<<<<<<<<<<<<<
@RequiresApi(api = Build.VERSION_CODES.Q)
private void activityResult(ActivityResult activityResult) throws IOException {

    Uri uri = null;
    if (activityResult.getData() != null) {
        uri = activityResult.getData().getData();
    }
    assert uri != null;
    uploadFileName = new Date().toString() + "." + getMimeType(getApplicationContext(),uri);

    File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFile");

    try {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        FileUtils.copy(inputStream, new FileOutputStream(uploadFile));
    } catch (Exception exception) {
        Log.e("activityResultFile", "onActivityResult:failed" + exception.toString());
    }

    Amplify.Storage.uploadFile(
            uploadFileName,
            uploadFile,
            success -> Log.i("activityResultFile", " succeeded " + success.getKey()),
            error -> Log.e("activityResultFile", " failed " + error.toString())

    );
}

    public static String getMimeType(Context context, Uri uri) {
        String extension;

        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    try {
                        activityResult(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });




    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
//        chooseFile.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{".jpg", ".png"});
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
//        startActivityForResult(chooseFile,29); // deprecated
        someActivityResultLauncher.launch(chooseFile);
        Toast.makeText(getBaseContext(), "Get File", Toast.LENGTH_SHORT).show();

//        openSomeActivityForResult();
    }



    private void uploadFileToS3() {
        File testFile = new File(getApplicationContext().getFilesDir(), "test");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(testFile));
            bufferedWriter.append("This is a test file to demonstrate S3 functionality");
            bufferedWriter.close();
        } catch (Exception exception) {
            Log.e(TAG, "uploadFileToS3: failed" + exception.toString());
        }

    }
    }


