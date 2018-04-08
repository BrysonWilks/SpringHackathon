package com.example.brysonwilks.roadwork;

import java.util.Date;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    int ManagerNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Storm").child("Road Name");
        myRef.setValue("Peterson");
    }
    public void managerSet(View view)
    {
        Intent managerSet = new Intent(this, Storm.class);
        startActivity(managerSet);
    }
}
