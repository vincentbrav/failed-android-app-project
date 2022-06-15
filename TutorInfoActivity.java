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
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.util.HashMap;
        import java.util.Map;

public class TutorInfoActivity extends AppCompatActivity {


    //Declerations
    private EditText etSchoolName;
    private EditText etDegree;
    private EditText etBios;
    private Button bSave;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String schoolName, degree, bios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_info);

        //Initialize
        etSchoolName = (EditText) findViewById(R.id.etSchoolName);
        etDegree = (EditText) findViewById(R.id.etDegree);
        etBios = (EditText) findViewById(R.id.etBios);
        bSave = (Button) findViewById(R.id.bSave);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser();

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    sendUserData();
                    Toast.makeText(TutorInfoActivity.this, "Save is successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TutorInfoActivity.this, StudentProfileActivity.class));
                } else {

                    Toast.makeText(TutorInfoActivity.this, "Please complete filling all fields", Toast.LENGTH_SHORT).show();

                }

            }

        });



    }



    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TutorInfoActivity.this, MainActivity.class));
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


    //tutor info activity validate function
    private Boolean validate(){
        Boolean result = false;

        schoolName = etSchoolName.getText().toString().trim();
        degree = etDegree.getText().toString().trim();
        bios = etBios.getText().toString().trim();



        if (schoolName.isEmpty() || degree.isEmpty() || bios.isEmpty()){
            Toast.makeText(this, "Please complete filling all fields", Toast.LENGTH_SHORT).show();
        } else{
            result = true;
        }

        return result;

    }


    //send user data to user data to firebase database
    private void sendUserData(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        TutorInfoClass tutorInfoClass = new TutorInfoClass(schoolName, degree, bios);
        myRef.setValue(tutorInfoClass);

    }
}
