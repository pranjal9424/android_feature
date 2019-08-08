package com.example.java_connectivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


private List<ListItem> listItems;
private Context context;

public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.listitem,parent,false);
        return new ViewHolder(v);

        }

@Override
public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);
        holder.textview_question.setText(listItem.getQuestion());
        holder.textview_answer.setText(listItem.getAnswer());
       /* Typeface typeface = Typeface.createFromAsset(holder.itemView.getContext().getAssets(), "comic.ttf");
        holder.textview_question.setTypeface(typeface);*/
        }

@Override
public int getItemCount() {

        return listItems.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView textview_question;
    public TextView textview_answer;


    public ViewHolder(View itemView) {
        super(itemView);

        textview_question = (TextView)itemView.findViewById(R.id.textquest);
        textview_answer = (TextView)itemView.findViewById(R.id.texxtanswer);
    }
}
}
