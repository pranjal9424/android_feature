package com.q_a.databasemanipulate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter_Q extends ArrayAdapter<Listitem_Q> {
    private List<Listitem_Q> arraylistItems;
    private Context context;

    public MyAdapter_Q(List<Listitem_Q> listItems,@NonNull Context context) {
        super(context,R.layout.listitem_q,listItems);
        this.arraylistItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_q,null,true);

        TextView tvq=view.findViewById(R.id.textquest);
        //TextView tva=view.findViewById(R.id.texxtanswer);

        tvq.setText(arraylistItems.get(position).getQuestion());
        //tva.setText(arraylistItems.get(position).getAnswer());
        return view;
    }
}


