package Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Models.User;
import Paths.ChooseDayes;
import com.example.smartparking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    EditText email,username,password;
    DatabaseReference database;
    long maxid=0;
    ArrayList <String> emailList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        email=findViewById(R.id.mail);
        username=findViewById(R.id.usrusr);
        password=findViewById(R.id.pswrdd);
        database = FirebaseDatabase.getInstance().getReference().child("Users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        emailList.add(ds.getValue(User.class).getEmail());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    public void Login(View v){finish();}


    public void register(View view) {
        if(email.getText().toString().isEmpty()||username.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast.makeText(this,"الرجاء اكمال جميع الحقول",Toast.LENGTH_SHORT).show();
            return;
        }
        System.out.println(emailList.size());
        for (int i = 0; i < emailList.size(); i++) {
            System.out.println(emailList.get(i));
            if(emailList.get(i).equals(email.getText()+"")){
                Toast.makeText(this,"هذا الاميل مسجل من قبل",Toast.LENGTH_SHORT).show();
                return;
            }
        }

        User u=new User();
        u.setEmail(email.getText()+"");
        u.setUsername(username.getText()+"");
        u.setPassword(password.getText()+"");
        emailList.add(email.getText()+"");
        database.child(maxid+1+"").setValue(u);
        Toast.makeText(this,"تم التسجيل بنجاح",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this, ChooseDayes.class);
        startActivity(i);
    }




}