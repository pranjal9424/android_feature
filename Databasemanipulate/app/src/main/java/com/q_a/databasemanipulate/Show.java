package com.q_a.databasemanipulate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Show extends AppCompatActivity {
    SwipeRefreshLayout mSwipeRefreshLayout;
    public static  String URI_DATA;
    private ListView listView;
    private MyAdapter_Q adapter;
    public static ArrayList<Listitem_Q> listItems = new ArrayList<>();
    public  static String str_Topic;
    String url ="https://compulsory-rug.000webhostapp.com/C.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_Q(listItems,this);

        retrieveData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listItems.get(position).getAnswer();

                startActivity(new Intent(getApplicationContext(),Answer.class)
                .putExtra("position",position));

            }
        });
    }

    public void retrieveData(){
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listItems.clear();

                        try {


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");

                            for (int i =0;i<jsonArray.length();i++){

                                JSONObject o = jsonArray.getJSONObject(i);
                                Listitem_Q item = new Listitem_Q(
                                        o.getString("question"),
                                        o.getString("answer"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_Q(listItems, getApplicationContext());
                            listView.setAdapter(adapter);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Show.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}