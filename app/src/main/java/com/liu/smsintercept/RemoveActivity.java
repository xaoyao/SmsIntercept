package com.liu.smsintercept;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RemoveActivity extends AppCompatActivity {
    private TextView tv_address;
    private TextView tv_reason;
    private Button btn_remove;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        tv_address= (TextView) findViewById(R.id.tv_address);
        tv_reason= (TextView) findViewById(R.id.tv_reason);
        btn_remove= (Button) findViewById(R.id.btn_remove);
        btn_cancel= (Button) findViewById(R.id.btn_cancel);
        //获取当前id
        Intent intent=getIntent();
        final int id=intent.getIntExtra("id",-1);
        if(id>-1){
            Blacklist blacklist=DBUtil.findBlacklistById(RemoveActivity.this, id);
            //显示数据
            tv_address.setText("手机号码："+blacklist.getAddress());
            tv_reason.setText("拉黑原因:"+blacklist.getReason());
            //点击按钮，删除数据
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除数据
                    if(DBUtil.deleteBlacklistById(RemoveActivity.this,id)){
                        Toast.makeText(RemoveActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RemoveActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                    }

                    startActivity(new Intent(RemoveActivity.this, MainActivity.class));
                }
            });
        }
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RemoveActivity.this,MainActivity.class));
            }
        });
    }

}
