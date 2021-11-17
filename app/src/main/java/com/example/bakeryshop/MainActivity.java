package com.example.bakeryshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText t1, t2, t3, t4;

    String emailpattern="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    ProgressDialog ProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void process(View view) {
        t1 = (EditText)findViewById(R.id.username);
        t2 = (EditText)findViewById(R.id.password);
        t3 = (EditText)findViewById(R.id.phone);
        t4 = (EditText)findViewById(R.id.email);
        ProgressDialog=new ProgressDialog(this);

        String username = t1.getText().toString().trim();
        String password = t2.getText().toString().trim();
        String phone = t3.getText().toString().trim();
        String email = t4.getText().toString().trim();

        if (!email.matches(emailpattern)){
            t4.setError("Enter Right Email");
        }
        else if(password.isEmpty()){
            t2.setError("Enter Right Password");
        }
        else if(phone.length()<10){
            t3.setError("Enter Valid Number");
        }
        else{
            ProgressDialog.setMessage("Please wait For The Registration....");
            ProgressDialog.setTitle("Registration");
            ProgressDialog.setCanceledOnTouchOutside(false);
            ProgressDialog.show();
            dataholder obj=new dataholder(username,password,phone,email);

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node=db.getReference("details");

            node.setValue(obj);

            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            Toast.makeText(getApplicationContext(),"Register successful",Toast.LENGTH_LONG).show();
            sendUserToNextActivity();
        }
    }

    private void sendUserToNextActivity() {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
    }
}