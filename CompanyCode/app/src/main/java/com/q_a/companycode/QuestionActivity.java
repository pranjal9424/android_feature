package com.q_a.companycode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import java.util.HashMap;
import java.util.Map;

public class QuestionActivity extends AppCompatActivity {
    private ListView listView;
    private MyAdapter_Question adapter;
    public static ArrayList<Listitem_question> listItems = new ArrayList<>();
    public  static final String url ="https://compulsory-rug.000webhostapp.com/companyQuestion.php";
    int position;
    String companyName;
    TextView cn;
    ProgressBar progressBar;

    LinearLayout searchBar;
    Button search;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        listView = (ListView) findViewById(R.id.recycleview);
        adapter = new MyAdapter_Question(listItems,this);

        ///////////////////////////////////////////
        progressBar=findViewById(R.id.load);

        retrieveData();

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        /////////////////// get selected company name
        companyName = MainActivity.listItems.get(position).getCompany();

        cn=findViewById(R.id.cn);
        cn.setText(companyName);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listItems.get(position).getAnswer();

                startActivity(new Intent(getApplicationContext(),CodeActivity.class)
                        .putExtra("position",position));

            }
        });




    }

    public void retrieveData(){
        progressBar.setVisibility(View.VISIBLE);
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
                                Listitem_question item = new Listitem_question(
                                        o.getString("question"),
                                        o.getString("answer"));
                                listItems.add(item);

                            }

                            adapter = new MyAdapter_Question(listItems, getApplicationContext());
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
                Toast.makeText(QuestionActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE );
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