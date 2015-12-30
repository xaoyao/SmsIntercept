package com.liu.smsintercept;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private ListView lv_blacklists;
    private List<Blacklist> blacklists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv_blacklists= (ListView) findViewById(R.id.lv_blacklists);
        btn_add= (Button) findViewById(R.id.btn_add);

        //初始化Blacklists
        this.initBlacklists();
        //显示数据
        lv_blacklists.setAdapter(new BlacklistsAdapter(this,R.layout.blacklist_item,blacklists));

        //add按钮点击事件
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
        blacklists=new ArrayList<>();
        Blacklist blacklist;
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //从数据库中查询所有的黑名单号码
        Cursor cursor=db.rawQuery("select * from blacklist", null);
        while (cursor.moveToNext()){
            blacklist=new Blacklist();
            blacklist.setId(cursor.getInt(cursor.getColumnIndex("id")));
            blacklist.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            blacklist.setReason(cursor.getString(cursor.getColumnIndex("reason")));
            //把得到的每条数据放入List中
            blacklists.add(blacklist);
        }


//        blacklists=new ArrayList<>();
//        blacklist=new Blacklist();
//        blacklist.setId(1);
//        blacklist.setAddress("123");
//        blacklist.setReason("123");
//        //把得到的每条数据放入List中
//        blacklists.add(blacklist);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //更新数据
        this.initBlacklists();
        lv_blacklists.setAdapter(new BlacklistsAdapter(this, R.layout.blacklist_item, blacklists));
    }
}
