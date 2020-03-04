package com.example.smartparking;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ChooseDayes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_choose_dayes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,getResources().getStringArray(R.array.c));
        Spinner spinner =findViewById(R.id.s1);
        spinner.setAdapter(adapter);
        Spinner spinner2 =findViewById(R.id.s2);
        spinner2.setAdapter(adapter);
        Spinner spinner3 =findViewById(R.id.s3);
        spinner3.setAdapter(adapter);
        Spinner spinner4 =findViewById(R.id.s4);
        spinner4.setAdapter(adapter);
        Spinner spinner5 =findViewById(R.id.s5);
        spinner5.setAdapter(adapter);
    }

    public void save(View v){
        Toast.makeText(this,"تم الحفظ",Toast.LENGTH_SHORT).show();
        startActivityForResult(new Intent(this,Nvm.class),101);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        startActivity(new Intent(this,ChooseC.class));
    }
}

