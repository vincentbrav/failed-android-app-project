package com.example.answercubeproto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentProfileActivity extends AppCompatActivity {

    //Declerations
    private ImageView ivDisplayStudentPicture;
    private TextView tvStudentName, tvSchoolName, tvMajorName;
    private Button bUseApp;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Initialize
        ivDisplayStudentPicture = (ImageView) findViewById(R.id.ivDisplayStudentPicture);
        tvStudentName = (TextView) findViewById(R.id.tvStudentName);
        tvSchoolName = (TextView) findViewById(R.id.tvSchoolName);
        tvMajorName = (TextView) findViewById(R.id.tvMajorName);
        bUseApp = (Button) findViewById(R.id.bUseApp);


        //firebase initializations
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();





//REF ONE

        //Pulling database reference to get database object
        DatabaseReference studentNameReference = firebaseDatabase.getReference(firebaseAuth.getUid());


        //pulling database reference to add value litsener
        //includes two functions one for displaying values in text view object and for catching errors
        studentNameReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfileClass userProfileClass = dataSnapshot.getValue(UserProfileClass.class);


                tvStudentName.setText("Student Name: " +  userProfileClass.getUserName());
//                tvMajorName.setText("Student Name: " +  userProfileClass.getUserName());
//                tvSchoolName.setText("Student Name: " +  userProfileClass.getUserName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }

        });

//REF TWO

        DatabaseReference studentInfoReference =  firebaseDatabase.getReference().child("Students");
        studentInfoReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StudentInfoClass studentInfoClass = dataSnapshot.getValue(StudentInfoClass.class);

                tvSchoolName.setText(studentInfoClass.getSchoolName());
                tvMajorName.setText(studentInfoClass.getMajorName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(StudentProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();


            }
        });




        //Button to send user to user the actual app function. (Take pictures)
        bUseApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentProfileActivity.this, StudentPictureProblemActivity.class));
            }
        });


    }



    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(StudentProfileActivity.this, MainActivity.class));
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
