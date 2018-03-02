package com.example.pawel.orlikapp.ui.menu.my_reservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.pawel.orlikapp.R;
import com.example.pawel.orlikapp.model.Booking;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Pawel on 02.03.2018.
 */

public class ExpantableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private String[] categories;
    private HashMap<String, List<Booking>> listHashMap;

    public ExpantableListAdapter(Context context, String[] categories, HashMap<String, List<Booking>> listHashMap) {
        this.context = context;
        this.categories = categories;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return categories.length;
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(categories[i]).size();
    }

    @Override
    public Object getGroup(int i) {
        return categories[i];
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(categories[i]).get(i1); //i = Group Item, i1 = Child Item
//        return listHashMap.get(i).get(i1);

    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headerTitle = (String) getGroup(i);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reservation_group, null);
        }
        TextView addres = (TextView) view.findViewById(R.id.groupHeader);
        addres.setText(headerTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Booking booking = (Booking) getChild(i, i1);
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reservation_item, null);
        }
        TextView addres = (TextView) view.findViewById(R.id.addresTextView);
        addres.setText(booking.getPlayground().getAddres());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
