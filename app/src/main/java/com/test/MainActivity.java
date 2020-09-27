package com.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.test.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvAdaptor.setText("binding adapter");
        new Thread() {
            @Override
            public void run() {
                testSynchronized();
            }
        }.start();
    }

    public void testJetPack(View view) {
        Test.testWorker(this);
        Test.roomTest();
    }

    public void testSynchronized() {
        for (int i = 0; i < 1000; i++) {
            new TestThread(i).start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void log(int i, int sleep) {
        Log.d("TEST1", "log:" + i);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class TestThread extends Thread {

        int i;

        public TestThread(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            Log.d("TEST1", "run:" + i);
            log(i, i == 0 ? 10000 : 100);
        }
    }
}
