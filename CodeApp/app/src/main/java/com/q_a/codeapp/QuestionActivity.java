package com.q_a.codeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import java.util.HashMap;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    public static  String URI_DATA;
    private ListView listView;
    private MyAdapter_Name adapter;
    public static ArrayList<Listitem_Name> listItems = new ArrayList<>();
    public  static String str_Topic;
    public  static final String url ="https://compulsory-rug.000webhostapp.com/companyQuestion.php";
    int position;
    String companyName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_Name(listItems,this);

        retrieveData();

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        companyName = MainActivity.listItems.get(position).getCompany();


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
                                Listitem_Name item = new Listitem_Name(
                                        o.getString("question"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_Name(listItems, getApplicationContext());
                            listView.setAdapter(adapter);
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuestionActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String,String> getParams(){
                Map<String, String > param=new HashMap<>();
                param.put("param",companyName);
                return param;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}