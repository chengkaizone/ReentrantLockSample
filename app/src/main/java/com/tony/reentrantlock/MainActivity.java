package com.tony.reentrantlock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            test();
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void test() throws InterruptedException {
        final ReentrantLockUtil reenlock = new ReentrantLockUtil();

        Thread thread0 = new Thread(new Runnable() {
            public void run() {
                try {
                    reenlock.step0();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                try {
                    reenlock.step1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread0.start();
        thread1.start();

        thread0.join();
        thread1.join();

        reenlock.end();
    }

}
