package com.test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;


@SuppressLint("NewApi")
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
