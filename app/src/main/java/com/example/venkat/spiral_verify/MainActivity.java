package com.example.venkat.spiral_verify;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements ZBarScannerView.ResultHandler{
    private ZBarScannerView mScannerView;
    String url;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    private DatabaseReference mDatabase;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view

    }



    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }
    @Override
    public void handleResult(Result result) {
        Log.v(TAG, result.getContents()); // Prints scan results
        Log.v(TAG, result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)
     //   Toast.makeText(this,result.getContents(),Toast.LENGTH_SHORT).show();

   //     Toast.makeText(this,result.getBarcodeFormat().getName(),Toast.LENGTH_SHORT).show();
        String info =result.getContents();
        Intent barCodeInfo=new Intent(this,Bar_result.class);
        barCodeInfo.putExtra("bar_Info_scan",info);
        startActivity(barCodeInfo);
        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);

    }

    public void rclk(View view) {
        mDatabase.child("reset").setValue(1);

    }

    public void scclk(View view) {
        setContentView(mScannerView);
    }
}
