package com.example.answercubeproto;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText etAge, etEmailAddress, etUsername, etPassword;
    private Button bRegister;
    private TextView tvAlreadyRegistered;

    //Public Declerations
    String age, username, password, emailAddress;

    //Firebase Authentification declaration
    private FirebaseAuth firebaseAuth;

    //Firebase Database declaration
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        tvAlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent());
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override

            //Firebase Auth functions implementation
            public void onClick(View v) {
                if(validate()){
                    String user_Email = etEmailAddress.getText().toString().trim();
                    String user_Password = etPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_Email, user_Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(RegisterActivity.this, "Successfully Registered, Upload Complete", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed,", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }



            }
        });

    }



    //function to set UI Views
    private void setupUIViews(){

        etAge = (EditText) findViewById(R.id.etAge);
        etEmailAddress = (EditText) findViewById(R.id.etEmailAddress);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        tvAlreadyRegistered = (TextView) findViewById(R.id.tvAlreadyRegistered);

        bRegister = (Button) findViewById(R.id.bRegister);
    }


    //function to validate
    private Boolean validate(){
        Boolean result = false;

         age = etAge.getText().toString();
         emailAddress = etEmailAddress.getText().toString();
         username = etUsername.getText().toString();
         password = etPassword.getText().toString();

        if (age.isEmpty() || emailAddress.isEmpty() || username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please complete filling all fields", Toast.LENGTH_SHORT).show();
        } else{
            result = true;
        }

        return result;

    }



    //function for sending email verification
    private void sendEmailVerification(){

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(RegisterActivity.this, "Successfully Registered, Verification email has been sent", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    } else{
                        Toast.makeText(RegisterActivity.this, "Error, Verification Email has not been sent!", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


    }




    //FirebaseDatabase
    private void sendUserData(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfileClass userProfileClass = new UserProfileClass(username, emailAddress, age);
        myRef.setValue(userProfileClass);
    }







}
