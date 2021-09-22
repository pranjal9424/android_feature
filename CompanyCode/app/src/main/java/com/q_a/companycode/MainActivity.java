package com.q_a.companycode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    private ListView listView;
    private MyAdapter_Name adapter;
    public static ArrayList<Listitem_Name> listItems = new ArrayList<>();
    public static final String  url ="https://compulsory-rug.000webhostapp.com/companyName.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_Name(listItems,this);




        /////////////////progress bar
        progressBar=findViewById(R.id.load);

        ////////////////////next activity set postion
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listItems.get(position).getCompany();

                startActivity(new Intent(getApplicationContext(),QuestionActivity.class)
                        .putExtra("position",position));

            }
        });
        retrieveData();
    }

    public void retrieveData(){
        progressBar.setVisibility(View.VISIBLE); //to show
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
                                Listitem_Name item = new Listitem_Name(
                                        o.getString("company"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_Name(listItems, getApplicationContext());
                            listView.setAdapter(adapter);

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}