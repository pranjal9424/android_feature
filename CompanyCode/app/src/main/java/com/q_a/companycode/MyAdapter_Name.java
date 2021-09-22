package com.q_a.companycode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter_Name extends ArrayAdapter<Listitem_Name> {
    private List<Listitem_Name> arraylistItems;
    private Context context;

    public MyAdapter_Name(List<Listitem_Name> listItems,@NonNull Context context) {
        super(context,R.layout.listitem_name,listItems);
        this.arraylistItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_name,null,true);

        TextView tvq=view.findViewById(R.id.textCompany);

        tvq.setText(arraylistItems.get(position).getCompany());
        return view;
    }
}