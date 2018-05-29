package com.example.ndh.week4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRoomClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.myroom");
        startActivity(intent);
    }

    public void onRealmClick(View view) {
        Intent intent= getPackageManager().getLaunchIntentForPackage("com.example.ndh.myrealm");
        startActivity(intent);
    }
}
