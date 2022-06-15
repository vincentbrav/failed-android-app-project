package com.example.answercubeproto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private TextView tvInfoText;
    private TextView tvRegisterHere;
    private TextView tvForgotPassword;
    private Button bLogin;
    private int counter = 5;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declerations
        etUsername  = (EditText) findViewById(R.id.etUsername);
        etPassword  = (EditText) findViewById(R.id.etPassword);
        tvInfoText = (TextView) findViewById(R.id.tvInfoText);
        tvRegisterHere = (TextView) findViewById(R.id.tvRegisterHere);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        tvInfoText.setText("Number of attempts remaining: ");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        //Object to get current user for database
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //Detecting if user is logged in.
        if (user != null){
            finish();
            startActivity(new Intent(MainActivity.this, UserAreaActivity.class));
        }


        //Listeners Section
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Passing parameters to the validate function.
                validate(etUsername.getText().toString(), etPassword.getText().toString());
            }
        });

        tvRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgotPasswordActivity.class));
            }
        });


    }


    //Validate function for logging in user.
    private void validate(String userName, String userPassword){

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //Object to sign in user with email and password from firebase database.
        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    checkEmailVerification();
                } else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    tvInfoText.setText("Number of attempts remaining: " + String.valueOf(counter));
                    if(counter == 0){
                        bLogin.setEnabled(false);
                    }
                }
            }
        });

    }


    //Function for email verificaition
    private void  checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailFlag = firebaseUser.isEmailVerified();

        startActivity(new Intent(MainActivity.this, StudentProfileActivity.class));


//        if (emailFlag){
//            Toast.makeText(MainActivity.this, "Login is Successful", Toast.LENGTH_SHORT).show();
//            finish();
//            startActivity(new Intent(MainActivity.this, UserAreaActivity.class));
//
//            Toast.makeText(this, "Verify your Email", Toast.LENGTH_SHORT);
//            firebaseAuth.signOut();
//        }

    }



}
