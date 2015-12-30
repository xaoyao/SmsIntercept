package com.liu.smsintercept;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private List<Blacklist> blacklists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //add按钮点击事件
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddActivity.class));
            }
        });
    }

    /**
     * 初始化BlackLists
     */
    private void initBlacklists(){
        Blacklist blacklist;
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //从数据库中查询所有的黑名单号码
        Cursor cursor=db.rawQuery("select * from blacklist", null);
        while (cursor.moveToNext()){
            blacklists=new ArrayList<>();
            blacklist=new Blacklist();
            blacklist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blacklist.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            blacklist.setReason(cursor.getString(cursor.getColumnIndex("reason")));
            blacklists.add(blacklist);
        }
    }
}
