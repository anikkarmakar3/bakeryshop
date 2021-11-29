package com.example.bakeryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bakeryshop-6715f-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username= findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);
        final Button loginbtn= findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernametext=username.getText().toString();
                final String passwordtext=Password.getText().toString();
                if (usernametext.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter right information",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("consumer").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usernametext)){
                                final String getPassword=snapshot.child(usernametext).child("password").getValue(String.class);
                                if (getPassword.equals(passwordtext)){
                                    Toast.makeText(getApplicationContext(),"successfully login",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(MainActivity.this,MainActivity2.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"wrong credentials",Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"wrong credentials",Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }


    public void create(View view) {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
    }
}