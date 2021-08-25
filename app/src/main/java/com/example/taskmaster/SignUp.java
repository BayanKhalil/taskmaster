package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "signUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        configureAmplify();

        Button signUp = findViewById(R.id.btnlogin);
        EditText username = findViewById(R.id.eteusername);
        EditText email = findViewById(R.id.etemail);
        EditText password = findViewById(R.id.mypass);

        signUp.setOnClickListener(view -> signUp(
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString()));
    }

    void signUp(String username, String email, String password) {
        Amplify.Auth.signUp(
                username,
                password,
                AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build(),
                success -> {
                    Log.i(TAG, "signUp successful: " + success.toString());
                    Intent goToVerification = new Intent(SignUp.this, Verification.class);
                    goToVerification.putExtra("username", username);
                    goToVerification.putExtra("password", password);
                    startActivity(goToVerification);
                },
                error -> Log.e(TAG, "signUp failed: " + error.toString()));
    }

//    private void configureAmplify() {
//        // configure Amplify plugins
//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i(TAG, "Successfully initialized Amplify plugins");
//        } catch (AmplifyException exception) {
//            Log.e(TAG, "Failed to initialize Amplify plugins => " + exception.toString());
//        }
//    }
}