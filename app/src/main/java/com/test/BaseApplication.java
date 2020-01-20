package com.test;

import android.app.Application;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.test.Jetpack.room.AppDatabase;

public class BaseApplication extends Application {

    private AppDatabase mAppDatabase;
    private static BaseApplication mApplication;

    @Override

    public void onCreate() {
        super.onCreate();
        mAppDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "android_room_dev.db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
        mApplication = this;
    }

    /**
     * 数据库版本 1->2 user表格新增了age列
     */
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {

        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User ADD COLUMN age integer");
        }
    };

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    public static BaseApplication getApplication() {
        return mApplication;
    }
}
