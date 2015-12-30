package com.liu.smsintercept;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liu on 2015/12/30 0030.
 */
public class BlacklistsAdapter extends ArrayAdapter<Blacklist>{
    private int resourceId;
    public BlacklistsAdapter(Context context, int resource, List<Blacklist> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取当前的Blacklist
        Blacklist blacklist=getItem(position);
        View view;
        ViewHolder viewHolder;
        //判断是否存在缓存
        if(convertView==null){
            view=View.inflate(getContext(),resourceId,null);
            viewHolder=new ViewHolder();
            viewHolder.tv_address= (TextView) view.findViewById(R.id.tv_address);
            viewHolder.tv_reason= (TextView) view.findViewById(R.id.tv_reason);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        //设置号码
        viewHolder.tv_address.setText(blacklist.getAddress());
        //设置拉黑原因
        viewHolder.tv_reason.setText(blacklist.getReason());

        return view;
    }


    class ViewHolder{
        TextView tv_address;
        TextView tv_reason;
    }
}
