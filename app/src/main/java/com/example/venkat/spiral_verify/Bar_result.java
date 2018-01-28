package com.example.venkat.spiral_verify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Bar_result extends AppCompatActivity {
    TextView barInfo;
    FirebaseDatabase database;
    DatabaseReference myref;
    String[] bcIn;
    TextView paystaus,amtstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_bar_result);
        barInfo = (TextView) findViewById(R.id.barInfo);
        Bundle bundle = getIntent().getExtras();
        String info = bundle.getString("bar_Info_scan");
        //barInfo.setText(info);
        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        bcIn=info.split(",");
        if(bcIn[1].equals("true")){
            Toast.makeText(this,"True Value",Toast.LENGTH_SHORT).show();
            setContentView(R.layout.pay_sucess);
            myref.child("Path").child("status").setValue(1);

        }
        else{
            setContentView(R.layout.activity_bar_result);
            paystaus=(TextView)findViewById(R.id.paystatus);
            amtstatus=(TextView)findViewById(R.id.amtstatus);
            amtstatus.setText(bcIn[0]);

        }

    }

    public void dkclk(View view) {
        myref.child("Path").child("status").setValue(1);
    }
}

