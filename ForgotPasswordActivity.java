package com.example.answercubeproto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etEmailPassword;
    private Button bResetPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etEmailPassword = (EditText) findViewById(R.id.etEmailAddress);
        bResetPassword = (Button) findViewById(R.id.bResetPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        bResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = etEmailPassword.getText().toString().trim();

                if(userEmail.equals("")){
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email address", Toast.LENGTH_SHORT ).show();
                } else {
                    firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Toast.makeText(ForgotPasswordActivity.this, "Password reset email has been sent!", Toast.LENGTH_SHORT ).show();
                            finish();
                            startActivity(new Intent(ForgotPasswordActivity.this, MainActivity.class));

                        }
                    });
                }
            }
        });

    }


}
