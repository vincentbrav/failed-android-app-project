package com.example.answercubeproto;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ProfileActivity extends AppCompatActivity {

    private ImageView ivUserImage;
    private TextView tvName, tvAge, tvEmail;
    private Button bSave;
    //private Firebase rootRef;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivUserImage = (ImageView)findViewById(R.id.ivUserImage);
        tvName = (TextView)findViewById(R.id.tvName);
        tvAge = (TextView) findViewById(R.id.tvAge);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        bSave = (Button) findViewById(R.id.bSave);


        //Firebase declerations
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase  = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("https://answercubeproto-e5102.firebaseio.com/");





        /*

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //StudentInfoClass studentInfo = dataSnapshot.getValue(StudentInfoClass.class);
                UserProfileClass userProfileClass = dataSnapshot.getValue(UserProfileClass.class);

                tvName.setText("Student Name: " + userProfileClass.getUserName());
                tvAge.setText("Age: " + userProfileClass.getUserAge());
                tvEmail.setText("Email: " + userProfileClass.getUserEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();
            }

        });

        */



    }
}
