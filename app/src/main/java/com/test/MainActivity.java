package com.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testJetPack(View view) {
        Test.testWorker(this);
        Test.roomTest();
    }

}
