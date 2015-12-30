package com.liu.smsintercept;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private Button btn_submit;
    private EditText et_address;
    private EditText et_reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn_submit= (Button) findViewById(R.id.btn_submit);
        et_address= (EditText) findViewById(R.id.et_address);
        et_reason= (EditText) findViewById(R.id.et_reason);

        //添加黑名单
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBlacklist();
                startActivity(new Intent(AddActivity.this,MainActivity.class));
            }
        });
    }

    /**
     * 添加到数据库
     */
    private void addBlacklist(){
        BlacklistDatabaseHelper dbHelper=new BlacklistDatabaseHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        //获取数据
        String address=et_address.getText().toString().trim();
        String reason=et_reason.getText().toString().trim();
        //号码不能为空
        if(address!=null&&!"".equals(address)){
            //开始事务
            db.beginTransaction();
            //插入数据
            try{
                ContentValues values=new ContentValues();
                values.put("address",address);
                values.put("reason",reason);
                db.insert("blacklist", null, values);
                db.setTransactionSuccessful();      //事务执行成功
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                db.endTransaction();        //结束事务
            }
        }
    }
}
