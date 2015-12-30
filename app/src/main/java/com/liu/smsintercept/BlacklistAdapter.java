package com.liu.smsintercept;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by liu on 2015/12/30 0030.
 */
public class BlacklistAdapter extends ArrayAdapter<Blacklist>{
    private int resourceId;
    public BlacklistAdapter(Context context, int resource, List<Blacklist> objects) {
        super(context, resource, objects);
        resourceId=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
