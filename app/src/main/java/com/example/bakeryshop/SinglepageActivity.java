package com.example.bakeryshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SinglepageActivity extends AppCompatActivity {
    ImageView img;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepage);
        img=(ImageView) findViewById(R.id.desc_img);
        tv1=(TextView) findViewById(R.id.desc_header);
        tv2=(TextView) findViewById(R.id.desc_desc);
        img.setImageResource(getIntent().getIntExtra("imagename",0));
        tv1.setText(getIntent().getStringExtra("header"));
        tv2.setText(getIntent().getStringExtra("desc"));
    }

    public void billing(View view) {
        Intent intent=new Intent(SinglepageActivity.this,BillingActivity.class);
        startActivity(intent);
    }
}