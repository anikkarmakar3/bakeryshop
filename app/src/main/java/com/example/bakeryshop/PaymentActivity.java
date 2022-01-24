package com.example.bakeryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

/*public class PaymentActivity extends AppCompatActivity */
public class PaymentActivity extends AppCompatActivity implements PaymentResultListener{
    TextView paytext;
    Button paybtn;
    EditText orderamount;
    TextView quantity;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Checkout.preload(getApplicationContext());

        orderamount=(EditText)findViewById(R.id.orderamount);
        paytext=(TextView)findViewById(R.id.orderid);
        quantity=(EditText)findViewById(R.id.orderquantity);
        result=(EditText)findViewById(R.id.orderresult);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a,b,c;
                a=Double.parseDouble(orderamount.getText().toString());
                b=Double.parseDouble(quantity.getText().toString());
                c=a*b;
                result.setText(Double.toString(c));
            }
        });
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
        checkout.setKeyID("rzp_test_4d10gsksDbQlhJ");

        /*checkout.setImage(R.drawable.logo);*/


        final Activity activity = this;


        try {
            JSONObject options = new JSONObject();

            options.put("name", "Bakery Shop");
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            /*String payment=orderamount.getText().toString();
            String totalamount=quantity.getText().toString();*/
            String totalresult=result.getText().toString();
            options.put("currency", "INR");
            /*double total=Double.parseDouble(payment);*/
            /* double total2=Double.parseDouble(totalamount);*/
            double total3=Double.parseDouble(totalresult);
            total3=total3*100;
            /*total=total*100;*/
            /*total2=total*total2;*/
            options.put("amount",total3);
            /*options.put("amount", "100");*///pass amount in currency subunits
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