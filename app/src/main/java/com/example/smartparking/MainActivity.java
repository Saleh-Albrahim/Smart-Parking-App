package com.example.smartparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
    }
    public void Signup(View v) {
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }
    public void start(View v) {
        Intent i=new Intent(this,ChooseDayes.class);
        startActivity(i);
    }
    public void goToNvm(View v) {
        Intent i=new Intent(this,ChooseC.class);
        startActivity(i);
    }
}