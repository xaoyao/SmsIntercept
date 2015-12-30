package com.liu.smsintercept;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 短信黑名单数据库
 * Created by liu on 2015/12/30 0030.
 */
public class BlacklistDatabaseHelper extends SQLiteOpenHelper{
    private static final String CREATE_BLACKLIST="" +
            "create table blacklist(" +
            "id integer primary key autoincrement," +
            "address text," +
            "reason text)";

    public BlacklistDatabaseHelper(Context context) {
        super(context, "blacklist.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库表
        db.execSQL(CREATE_BLACKLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
