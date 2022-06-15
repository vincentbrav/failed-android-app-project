package com.example.answercubeproto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class TutorProfileActivity extends AppCompatActivity {
    private EditText etDisplayTutorName, etDisplaySchoolName, etDisplayBios;
    private Button bSave, bSignOut;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);
        etDisplayTutorName = (EditText)findViewById(R.id.etDisplayTutorName);
        etDisplaySchoolName = (EditText) findViewById(R.id.etDisplaySchoolName);
        etDisplayBios = (EditText) findViewById(R.id.etDisplayBios);

        bSave = (Button) findViewById(R.id.bSave);
        bSignOut = (Button) findViewById(R.id.bSignOut);

        firebaseAuth = FirebaseAuth.getInstance();

    }

    //Logot functions
    private void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(TutorProfileActivity.this, MainActivity.class));
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
