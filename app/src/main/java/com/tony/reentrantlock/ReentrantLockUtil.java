package com.tony.reentrantlock;

import android.util.Log;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lance on 16/8/22.
 */
public class ReentrantLockUtil {
    private final String TAG = "ReentrantLockUtil";

    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void step0() throws InterruptedException {
        lock.lock();
        Log.i(TAG, "进入等待状态..." + count);
        condition.await();
        try {
            for (int i = 0; i < 1000; i++) {
                count++;

                Log.i(TAG, "================ " + count);
            }
        } finally {
            lock.unlock();
        }
    }

    public void step1() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        Log.i(TAG, "请点击Enter键" + count);
        new Scanner(System.in).nextLine();

        condition.signal();
        try {
            for (int i = 0; i < 1000; i++) {
                count++;
                Log.i(TAG, "**************** " + count);
            }
        } finally {
            lock.unlock();
        }
    }

    public void end() {

        Log.i(TAG, "end count: " + count);
    }

}
