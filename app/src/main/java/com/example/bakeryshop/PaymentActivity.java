package com.example.bakeryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

/*public class PaymentActivity extends AppCompatActivity */
public class PaymentActivity extends AppCompatActivity implements PaymentResultListener{
    TextView paytext;
    Button paybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());

        paytext=(TextView)findViewById(R.id.orderid);
        paybtn=(Button)findViewById(R.id.orderbtn);
        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makepayment();

            }
        });
    }

    private void makepayment() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_bj2JWfBH43zqDz");

        /*checkout.setImage(R.drawable.logo);*/


        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Bakery Shop");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "100");//pass amount in currency subunits
            options.put("prefill.email", "anikkarmakar3@gmail.com");
            options.put("prefill.contact","7001822650");
            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        paytext.setText("Successful payment id: "+s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        paytext.setText("Payment Failed: "+s);
    }
}
