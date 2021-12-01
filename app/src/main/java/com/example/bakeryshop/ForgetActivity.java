package com.example.bakeryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class ForgetActivity extends AppCompatActivity {


    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://bakeryshop-6715f-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        final EditText username= findViewById(R.id.username);
        final EditText Password = findViewById(R.id.password);
        final Button resetbtn= findViewById(R.id.reset);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernametext=username.getText().toString();
                final String passwordtext=Password.getText().toString();
                if (usernametext.isEmpty() || passwordtext.isEmpty()){
                    Toast.makeText(getApplicationContext(),"please enter right information",Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("consumer").addListenerForSingleValueEvent(new ValueEventListener(){

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usernametext)){
                                final String getPassword=snapshot.child(usernametext).child("password").getValue(String.class);
                                if (getPassword.equals(passwordtext)){
                                    Toast.makeText(getApplicationContext(),"Previous Password Is Same",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ForgetActivity.this,MainActivity.class));
                                    /*finish();*/
                                }
                                else {
                                    /*dataholder3 obj=new dataholder3(getPassword);*/
                                    databaseReference.child("consumer").child(usernametext).child("password").setValue(passwordtext);
                                    Toast.makeText(getApplicationContext(),"Reset Password Successfuly",Toast.LENGTH_LONG).show();
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
}
