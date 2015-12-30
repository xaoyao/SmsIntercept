package com.liu.smsintercept;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2015/12/30 0030.
 */
public class DBUtil {

    /**
     * 通过号码查询黑名单
     * @param context
     * @param address
     * @return
     */
    public static Blacklist findBlacklistByAddress(Context context,String address){
        Blacklist blacklist=null;
        List<Blacklist> list=null;
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //根据address查询数据
        Cursor cursor=db.rawQuery("select * from blacklist where address=?", new String[]{address});
        while (cursor.moveToNext()){
            list=new ArrayList<>();
            blacklist=new Blacklist();
            blacklist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blacklist.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            blacklist.setReason(cursor.getString(cursor.getColumnIndex("reason")));
            list.add(blacklist);
        }

        if(list!=null){
            blacklist=list.get(0);
        }
        return blacklist;
    }

    /**
     * 通过id获取blacklist
     * @param context
     * @param id
     * @return
     */
    public static Blacklist findBlacklistById(Context context,int id){
        Blacklist blacklist=null;
        List<Blacklist> list=null;
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //根据address查询数据
        Cursor cursor=db.rawQuery("select * from blacklist where id=?", new String[]{id+""});
        while (cursor.moveToNext()){
            list=new ArrayList<>();
            blacklist=new Blacklist();
            blacklist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blacklist.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            blacklist.setReason(cursor.getString(cursor.getColumnIndex("reason")));
            list.add(blacklist);
        }

        if(list!=null){
            blacklist=list.get(0);
        }
        return blacklist;
    }

    /**
     *通过id删除blacklist
     * @param id
     */
    public static void deleteBlacklistById( Context context,int id){
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(context);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete("blacklist","id=?",new String[]{id+""});
    }
}
