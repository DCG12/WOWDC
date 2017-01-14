package com.example.user.wowdc;

import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.List;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class WOWAdapter extends ArrayAdapter<WOW> {

    public WOWAdapter(Context context, int resource, List<WOW> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        WOW wow = getItem(position);
        Log.w("XXXX", wow.toString());

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_wow_row, parent, false);
        }

        TextView lvBossName = (TextView) convertView.findViewById(R.id.lvBossName);
        TextView lvBossLevel = (TextView) convertView.findViewById(R.id.lvBossLevel);

        lvBossName.setText(wow.getName());
        lvBossLevel.setText("Nivel: " + wow.getLevel());

        return convertView;
    }
}