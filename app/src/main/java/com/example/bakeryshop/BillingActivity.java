package com.example.bakeryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;

public class BillingActivity extends AppCompatActivity {
    ProgressDialog ProgressDialog;
    TextView txt1,txt2,txt3,txt4,txt5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        Checkout.preload(getApplicationContext());
        setTitle("Hello This Is Billing Process");

    }

    public void savebill(View view) {
        txt1=(EditText)findViewById(R.id.address);
        txt2=(EditText)findViewById(R.id.quantity);
        txt3=(EditText)findViewById(R.id.pincode);
        txt4=(EditText)findViewById(R.id.phone);
        txt5=(EditText)findViewById(R.id.billemail);
        ProgressDialog=new ProgressDialog(this);

        String address=txt1.getText().toString().trim();
        String quantity=txt2.getText().toString().trim();
        String pincode=txt3.getText().toString().trim();
        String phone=txt4.getText().toString().trim();
        String billemail=txt5.getText().toString().trim();

        if (address.isEmpty()){
            txt1.setError("Please enter Address");
        }
        else if (quantity.isEmpty()){
            txt2.setError("Enter Quantity");
        }
        else if (pincode.isEmpty()){
            txt3.setError("enter pincode");
        }
        else if (phone.isEmpty()){
            txt4.setError("Enter Phone");
        }
        else {
            ProgressDialog.setMessage("Please wait For The Save Data....");
            ProgressDialog.setTitle("Address Saving");
            ProgressDialog.show();
            dataholder2 obj=new dataholder2(address,quantity,pincode,phone);
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference node1=db.getReference("order");

            node1.child(billemail).setValue(obj);
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");

            Toast.makeText(getApplicationContext(),"Address Save successful",Toast.LENGTH_LONG).show();
            ProgressDialog.hide();
            sendUserToPaymentPage();
        }
    }
    private void sendUserToPaymentPage() {
        Intent intent=new Intent(BillingActivity.this,PaymentActivity.class);
        /*intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);*/
        startActivity(intent);
    }
}