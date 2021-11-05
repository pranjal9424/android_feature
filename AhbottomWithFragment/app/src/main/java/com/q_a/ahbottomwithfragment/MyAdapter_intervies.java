package com.q_a.ahbottomwithfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter_intervies extends ArrayAdapter<Listitem_question> {
    private List<Listitem_question> arraylistItems;
    private Context context;

    public MyAdapter_intervies(List<Listitem_question> listItems,@NonNull Context context) {
        super(context, R.layout.listitem_question,listItems);
        this.arraylistItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_interview_question, null, true);

        TextView tvq = view.findViewById(R.id.textCompany);

        tvq.setText(arraylistItems.get(position).getQuestion());
        return view;
    }
}
