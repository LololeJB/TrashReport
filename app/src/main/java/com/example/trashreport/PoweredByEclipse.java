package com.example.trashreport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class PoweredByEclipse extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poweredbyeclipse);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent accessMenu = new Intent(PoweredByEclipse.this,MainActivity.class);
                startActivity(accessMenu);
            }
        }, 2000);   //2 seconds
    }
}
