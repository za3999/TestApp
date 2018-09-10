package com.test;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.test.dao.User;
import com.test.db.DaoMaster;
import com.test.db.DaoSession;

public class CustomApplication extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    public static CustomApplication instances;

    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();
        mDaoSession.getUserDao().insert(new User(null, "郑小福", "12345"));
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "sport-db", null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
