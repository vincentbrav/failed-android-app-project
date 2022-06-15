package com.example.answercubeproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserAreaActivity extends AppCompatActivity {
    private TextView tvWelcomeMessage;
    private TextView tvStudentorTutor;
    private Button bStudent;
    private Button bTutor;
    private Button bProfile;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        tvStudentorTutor = (TextView) findViewById(R.id.tvStudentorTutor);

        bStudent = (Button) findViewById(R.id.bStudent);
        bTutor = (Button) findViewById(R.id.bTutor);
        bProfile = (Button) findViewById(R.id.bProfile);

        firebaseAuth = FirebaseAuth.getInstance();



        bProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAreaActivity.this, ProfileActivity.class));
            }
        });


        bStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAreaActivity.this, StudentInfoActivity.class));
            }
        });

        bTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserAreaActivity.this, TutorInfoActivity.class));

            }
        });




    }

    //Logout functions
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(UserAreaActivity.this, MainActivity.class));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logoutMenu:{
              Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }



}
