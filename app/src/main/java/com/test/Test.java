package com.test;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.room.Room;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.test.Jetpack.room.AppDatabase;
import com.test.Jetpack.room.User;
import com.test.Jetpack.room.UserDao;
import com.test.Jetpack.workmanager.TestWorker;
import com.test.bean.TestBean;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void testWorker(LifecycleOwner lifecycleOwner) {
        WorkManager.getInstance().cancelAllWork();
        Constraints myConstraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();
        Data data = new Data.Builder().putString("key", "testWork").build();
//        //只执行一次的Work
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(TestWorker.class)
//                .setInputData(data).setInitialDelay(5, TimeUnit.SECONDS).setConstraints(myConstraints).build();
//        WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId()).observe(lifecycleOwner, workInfo -> {
//            if (WorkInfo.State.SUCCEEDED == workInfo.getState()) {
//                Log.d("test", "dowork success:" + Thread.currentThread().getName());
//            }
//        });
//        WorkManager.getInstance().enqueue(request);
//        //循环执行的Work,间隔不能小于15分钟
//        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(TestWorker.class, 15, TimeUnit.MINUTES)
//                .setInputData(data).setConstraints(myConstraints).build();
//        WorkManager.getInstance().enqueue(periodicWorkRequest);

        //链式条件执行
        data = new Data.Builder().putString("key", "requestA").build();
        OneTimeWorkRequest requestA = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).setConstraints(myConstraints).build();
        data = new Data.Builder().putString("key", "requestB").build();
        OneTimeWorkRequest requestB = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).setConstraints(myConstraints).build();
        data = new Data.Builder().putString("key", "requestC").build();
        OneTimeWorkRequest requestC = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).setConstraints(myConstraints).build();
        data = new Data.Builder().putString("key", "requestD").build();
        OneTimeWorkRequest requestD = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).setConstraints(myConstraints).build();
        data = new Data.Builder().putString("key", "requestE").build();
        OneTimeWorkRequest requestE = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(data).setConstraints(myConstraints).build();
        WorkContinuation c1 = WorkManager.getInstance().beginWith(requestA).then(requestC);
        WorkContinuation c2 = WorkManager.getInstance().beginWith(requestB).then(requestD);
        WorkContinuation c = WorkContinuation.combine(Arrays.asList(c1, c2)).then(requestE);
        c.enqueue();
    }

    public static void roomTest() {
        UserDao userDao = BaseApplication.getApplication().getAppDatabase().userDao();
        userDao.insertAll(new User("郑", "小才"));
        Log.d("test", "result:" + userDao.getAll());
    }

    public static void testBean(){
        TestBean testBean = new TestBean("张三");
        Log.d("TEST1", testBean.getName());
        update(testBean);
        Log.d("TEST1", testBean.getName());
    }

    public static TestBean update(TestBean testBean) {
        testBean = new TestBean("李四");
        Log.d("TEST1", testBean.getName());
        return testBean;
    }
}
