package com.example.answercubeproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentInfoActivity extends AppCompatActivity {

    private EditText etSchoolName;
    private EditText etMajor;
    private EditText etClassName;
    private Button bSave;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseData;
    String schoolName, majorName, className;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        etSchoolName = (EditText) findViewById(R.id.etSchoolName);
        etMajor = (EditText) findViewById(R.id.etMajor);
        etClassName = (EditText) findViewById(R.id.etClassName);
        bSave = (Button) findViewById(R.id.bSave);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendUserData();
                    Toast.makeText(StudentInfoActivity.this, "Save is successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(StudentInfoActivity.this, StudentProfileActivity.class));

                } else{
                    Toast.makeText(StudentInfoActivity.this, "Please complete filling all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    //Logot functions
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StudentInfoActivity.this, MainActivity.class));



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



    //sendUserData function
    public void sendUserData(){
        firebaseData = FirebaseDatabase.getInstance();

//        StudentInfoClass studentInfo = new StudentInfoClass(schoolName, majorName, className);
//        UserProfileClass userProfileClass = new UserProfileClass();
//
//
//        DatabaseReference myRef;
//        //myRef = firebaseData.getReference("https://answercubeproto-e5102.firebaseio.com/Student").child("Student");
//        myRef = firebaseData.getReference().child("Class");
//        myRef.push().setValue(studentInfo);

        DatabaseReference myRef = firebaseData.getReference().child("Students");
        StudentInfoClass studentInfo = new StudentInfoClass(schoolName, majorName, className);
        myRef.setValue(studentInfo);
    }






    private Boolean validate(){
        Boolean result = false;

        schoolName = etSchoolName.getText().toString().trim();
        majorName = etMajor.getText().toString().trim();
        className = etClassName.getText().toString().trim();



        if (schoolName.isEmpty() || majorName.isEmpty() || className.isEmpty()){
            Toast.makeText(this, "Please complete filling all fields", Toast.LENGTH_SHORT).show();
        } else{
            result = true;
        }

        return result;

    }




}
