package Main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import Maps.ShortCut;
import Models.User;
import Paths.ChooseDayes;
import com.example.smartparking.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseReference database,ref;
    long maxid=0;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance().getReference().child("Users");
        username=findViewById(R.id.usernasme);
        password=findViewById(R.id.password);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    maxid=(dataSnapshot.getChildrenCount());
                }
                Addusers();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void Signup(View v) {
        Intent intent=new Intent(this, Register.class);

        startActivity(intent);


    }
    public void start(View v) {
        boolean  test=true;
        for (int i = 0; i <userList.size() ; i++) {
            if(username.getText().toString().equals(userList.get(i).getUsername())&&password.getText().toString().equals(userList.get(i).getPassword())){
                Toast.makeText(this,"مرحبا بك",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this, ChooseDayes.class);
                test=false;
                startActivity(intent);
            }
        }
        if(test)
        Toast.makeText(this,"خطأ في الاسم او في كلمة السر",Toast.LENGTH_SHORT).show();

    }
    public void goToNvm(View v) {
        Intent i=new Intent(this, ShortCut.class);
        startActivity(i);
    }
    ArrayList<User> userList=new ArrayList<>();
    public void Addusers(){

            ref = FirebaseDatabase.getInstance().getReference().child("Users");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        User d = new User();
                        d.setUsername(ds.getValue(User.class).getUsername());
                        d.setPassword(ds.getValue(User.class).getPassword());
                        userList.add(d);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
